package com.ld.lib_base.api.retrofit

import androidx.lifecycle.LiveData
import com.ld.lib_base.bean.login.UserInfoBean
import com.ld.lib_base.network.network.ApiResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 *  author : ld
 *  time   : 2021/01/15
 *  desc   :Retrofit-登录Api
 */
interface LoginApiRetrofit {

    companion object {
        const val SERVER_URL = "https://wanandroid.com/"
    }

    /**
     * 登录-其他模块使用（非协程）
     */
    @FormUrlEncoded
    @POST("user/login")
    fun loginNotCoroutine(
        @Field("username") username: String,
        @Field("password") password: String
    ): LiveData<ApiResponse<UserInfoBean>>
}