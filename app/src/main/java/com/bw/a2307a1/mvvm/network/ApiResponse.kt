package com.bw.a2307a1.mvvm.network

import com.google.gson.annotations.SerializedName

/**
 * 通用 API 响应封装
 * 根据后端约定修改 code、message、data 字段名
 */
data class ApiResponse<T>(
    @SerializedName("code") val code: Int = -1,
    @SerializedName("message") val message: String? = null,
    @SerializedName("data") val data: T? = null
) {
    fun isSuccess(): Boolean = code == 0 || code == 200

    fun getDataOrThrow(): T {
        if (!isSuccess()) throw ApiException(code, message ?: "请求失败")
        return data ?: throw ApiException(code, "数据为空")
    }
}

class ApiException(val code: Int, override val message: String) : Exception(message)
