package com.ld.lib_base.ext

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kingja.loadsir.core.LoadService
import com.ld.lib_base.base.viewmodel.BaseViewModel
import com.ld.lib_base.ext.util.logd
import com.ld.lib_base.ext.util.loge
import com.ld.lib_base.constant.Constant
import com.ld.lib_base.network.network.BaseResponse
import com.ld.lib_base.state.ResultState
import com.ld.lib_base.state.paresException
import com.ld.lib_base.state.paresResult
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 *  author : ld
 *  time   : 2021/01/07
 *  desc   :BaseViewModel 请求协程封装
 */

/**
 * net request 不校验请求结果数据是否是成功
 * @param block 请求体方法
 * @param resultState 请求回调的ResultState数据
 * @param isShowDialog 是否显示加载框
 * @param loadingMessage 加载框提示内容
 */
fun <T> BaseViewModel.request(
    block: suspend () -> BaseResponse<T>,
    resultState: MutableLiveData<ResultState<T>>,
    isShowDialog: Boolean = false,
    loadingMessage: String = Constant.STRING_NET_LOADING
): Job {

    return viewModelScope.launch {
        kotlin.runCatching {
            if (isShowDialog) loadsir.showLoading()
            //请求体
            block()
        }.onSuccess {
            loadsir.showSuccess()
            resultState.paresResult(it)
        }.onFailure {
            it.message?.logd()
            loadsir.showError(loadingMessage)
            resultState.paresException(it)
        }
    }
}


/**
 * net request 不校验请求结果数据是否是成功
 * @param block 请求体方法
 * @param resultState 请求回调的ResultState数据
 * @param isShowDialog 是否显示加载框
 * @param loadingMessage 加载框提示内容
 */
fun <T> BaseViewModel.requestNoCheck(
    block: suspend () -> T,
    resultState: MutableLiveData<ResultState<T>>,
    loadsir: LoadService<Any>,
    isShowDialog: Boolean = false,
    loadingMessage: String = Constant.STRING_NET_LOADING
): Job {
    return viewModelScope.launch {
        runCatching {
            if (isShowDialog) loadsir.showLoading()
            //请求体
            block()
        }.onSuccess {
            loadsir.showSuccess()
            resultState.paresResult(it)
        }.onFailure {
            it.message?.loge()
            loadsir.showError(loadingMessage)
            resultState.paresException(it)
        }
    }
}