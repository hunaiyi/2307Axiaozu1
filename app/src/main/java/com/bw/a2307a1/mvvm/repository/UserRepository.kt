package com.bw.a2307a1.mvvm.repository

import com.bw.a2307a1.mvvm.base.BaseRepository
import com.bw.a2307a1.mvvm.model.User
import com.bw.a2307a1.mvvm.network.ApiService

/**
 * 用户数据仓库（示例）
 */
class UserRepository : BaseRepository() {

    suspend fun getUserInfo(userId: Long): Result<User> = safeCall {
        ApiService.userApi.getUserInfo(userId).getDataOrThrow()
    }
}
