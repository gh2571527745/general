package com.ld.lib_base.ext

import com.ld.lib_base.arouter.homeApi
import com.ld.lib_base.bean.home.ApiPagerResponse
import com.ld.lib_base.bean.home.AriticleResponse
import com.ld.lib_base.network.network.ApiResponse
import com.ld.lib_base.util.CacheUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

/**
 *  author : ld
 *  time   : 2021/01/13
 *  desc   :协程请求类
 */
val HttpRequestCoroutine: HttpRequestManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
    HttpRequestManager()
}

class HttpRequestManager {
    /**
     * 获取首页文章数据-(同时异步请求2个接口，请求完成后合并数据)
     */
    suspend fun getHomeData(pageNo: Int): ApiResponse<ApiPagerResponse<ArrayList<AriticleResponse>>> {
        return withContext(Dispatchers.IO) {
            val data = async { homeApi().getAritrilList(pageNo) }
            //如果App配置打开了首页请求置顶文章，且是第一页
            if (CacheUtil.isNeedTop() && pageNo == 0) {
                val topData = async { homeApi().getTopAritrilList() }
                data.await().data.datas.addAll(0, topData.await().data)
                data.await()
            } else {
                data.await()
            }
        }
    }
}