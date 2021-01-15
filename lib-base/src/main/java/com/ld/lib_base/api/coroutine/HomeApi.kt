package com.ld.lib_base.api.coroutine

import com.ld.lib_base.bean.home.ApiPagerResponse
import com.ld.lib_base.bean.home.AriticleResponse
import com.ld.lib_base.network.network.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path

/**
 *  author : ld
 *  time   : 2021/01/07
 *  desc   :
 */
interface HomeApi {
    companion object {
        const val SERVER_URL = "https://wanandroid.com/"
    }

    /**
     * 获取置顶文章集合数据
     */
    @GET("article/top/json")
    suspend fun getTopAritrilList(): ApiResponse<ArrayList<AriticleResponse>>

    /**
     * 获取首页文章数据
     */
    @GET("article/list/{page}/json")
    suspend fun getAritrilList(@Path("page") pageNo: Int): ApiResponse<ApiPagerResponse<ArrayList<AriticleResponse>>>
}