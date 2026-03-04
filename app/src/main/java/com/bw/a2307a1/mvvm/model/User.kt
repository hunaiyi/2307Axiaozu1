package com.bw.a2307a1.mvvm.model

import com.google.gson.annotations.SerializedName

/**
 * 用户数据模型（示例）
 * 根据实际 API 字段调整
 */
data class User(
    @SerializedName("id") val id: Long = 0,
    @SerializedName("name") val name: String = "",
    @SerializedName("avatar") val avatar: String? = null,
    @SerializedName("phone") val phone: String? = null
)
