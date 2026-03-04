package com.bw.a2307a1.mvvm.network

import com.bw.a2307a1.mvvm.model.User
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://your-api-domain.com/"

private val loggingInterceptor = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}

private val okHttpClient = OkHttpClient.Builder()
    .connectTimeout(30, TimeUnit.SECONDS)
    .readTimeout(30, TimeUnit.SECONDS)
    .writeTimeout(30, TimeUnit.SECONDS)
    .addInterceptor(loggingInterceptor)
    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .client(okHttpClient)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

/**
 * 用户相关 API 接口（示例）
 */
interface UserApi {
    @GET("user/info")
    suspend fun getUserInfo(@Query("userId") userId: Long): ApiResponse<User>
}

object ApiService {
    val userApi: UserApi by lazy { retrofit.create(UserApi::class.java) }
}
