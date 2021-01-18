package com.ld.lib_base.api.coroutine

import com.ld.lib_base.bean.login.UserInfoBean
import com.ld.lib_base.network.network.ApiResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 *  author : ld
 *  time   : 2021/01/07
 *  desc   :协程-其他Api
 */
interface OtherApi {

    companion object {
        const val SERVER_URL = "https://wanandroid.com/"
    }

    /**
     * 登录
     */
    @FormUrlEncoded
    @POST("user/login")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") pwd: String
    ): ApiResponse<UserInfoBean>
}