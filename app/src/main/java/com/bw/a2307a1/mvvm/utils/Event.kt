package com.bw.a2307a1.mvvm.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

/**
 * 一次性事件封装，确保事件只被消费一次（如 Toast、导航等）
 */
class Event<out T>(private val content: T) {
    private var hasBeenHandled = false

    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) null else {
            hasBeenHandled = true
            content
        }
    }

    fun peekContent(): T = content
}

/**
 * 可观察的一次性事件 LiveData
 */
typealias MutableLiveEvent<T> = MutableLiveData<Event<T>>
typealias LiveEvent<T> = LiveData<Event<T>>

fun <T> MutableLiveData<Event<T>>.postEvent(value: T) {
    postValue(Event(value))
}

fun <T> MutableLiveData<Event<T>>.setEvent(value: T) {
    setValue(Event(value))
}

/**
 * 在 Activity/Fragment 中观察一次性事件，只处理一次
 * 用法: viewModel.xxxEvent.observeEvent(this) { value -> ... }
 */
fun <T> LiveData<Event<T>>.observeEvent(owner: LifecycleOwner, onEvent: (T) -> Unit) {
    observe(owner, Observer { event ->
        event.getContentIfNotHandled()?.let { onEvent(it) }
    })
}
