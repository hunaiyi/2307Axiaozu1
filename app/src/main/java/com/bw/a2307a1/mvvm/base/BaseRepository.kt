package com.bw.a2307a1.mvvm.base

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

/**
 * Repository 基类
 * - 在 IO 线程执行网络/数据库操作
 * - 统一异常捕获，返回 Result
 */
abstract class BaseRepository {

    /**
     * 安全执行挂起函数，将异常转为 Result
     */
    protected suspend fun <T> safeCall(
        call: suspend () -> T
    ): Result<T> = withContext(Dispatchers.IO) {
        try {
            Result.success(call())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
