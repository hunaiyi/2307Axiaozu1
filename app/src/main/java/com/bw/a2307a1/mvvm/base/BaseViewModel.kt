package com.bw.a2307a1.mvvm.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

/**
 * ViewModel 基类
 * - 提供 viewModelScope 的封装启动方法
 * - 统一异常处理
 */
abstract class BaseViewModel : ViewModel() {

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError(throwable)
    }

    /**
     * 在 ViewModel 作用域内启动协程，带统一异常处理
     */
    protected fun launchOnViewModelScope(
        context: CoroutineContext = EmptyCoroutineContext,
        block: suspend CoroutineScope.() -> Unit
    ) {
        viewModelScope.launch(context + exceptionHandler, block = block)
    }

    /**
     * 子类可重写以统一处理错误
     */
    protected open fun onError(throwable: Throwable) {
        // 可在此记录日志或上报
    }

    /**
     * 在主线程执行
     */
    protected suspend fun <T> withMainContext(block: suspend CoroutineScope.() -> T): T {
        return withContext(kotlinx.coroutines.Dispatchers.Main, block)
    }

    /**
     * 统一处理 Result，在主线程回调
     */
    protected suspend fun <T> handleResult(
        result: Result<T>,
        onSuccess: (T) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        withMainContext {
            result.fold(
                onSuccess = onSuccess,
                onFailure = onError
            )
        }
    }
}
