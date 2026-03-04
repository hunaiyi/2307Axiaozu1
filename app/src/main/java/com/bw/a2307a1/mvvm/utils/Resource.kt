package com.bw.a2307a1.mvvm.utils

/**
 * 数据状态封装，用于 UI 层统一处理加载/成功/失败/空数据
 */
sealed class Resource<out T> {
    data class Loading(val message: String? = null) : Resource<Nothing>()
    data class Success<T>(val data: T) : Resource<T>()
    data class Error(val message: String, val throwable: Throwable? = null) : Resource<Nothing>()
    data object Empty : Resource<Nothing>()
}
