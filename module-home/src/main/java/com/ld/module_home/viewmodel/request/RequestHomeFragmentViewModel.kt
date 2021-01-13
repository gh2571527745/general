package com.ld.module_home.viewmodel.request

import androidx.lifecycle.MutableLiveData
import com.ld.lib_base.base.viewmodel.BaseViewModel
import com.ld.lib_base.bean.ListDataUiState
import com.ld.lib_base.bean.home.ApiPagerResponse
import com.ld.lib_base.bean.home.AriticleResponse
import com.ld.lib_base.ext.HttpRequestCoroutine
import com.ld.lib_base.ext.request
import com.ld.lib_base.ext.requestNoCheck
import com.ld.lib_base.network.network.ApiResponse
import com.ld.lib_base.state.ResultState

/**
 *  author : ld
 *  time   : 2021/01/13
 *  desc   :
 */
class RequestHomeFragmentViewModel : BaseViewModel() {
    //页码
    var pageNo = 0


    //首页文章列表数据
    var homeDataState =
        MutableLiveData<ResultState<ApiResponse<ApiPagerResponse<ArrayList<AriticleResponse>>>>>()

    fun getHomeData(isRefresh: Boolean) {
        if (isRefresh) {
            pageNo = 0
        }else{
            pageNo++
        }
        requestNoCheck({ HttpRequestCoroutine.getHomeData(pageNo) }, homeDataState, false)
    }
}