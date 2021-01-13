package com.ld.lib_base.ext

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.LogUtils
import com.ld.lib_base.base.activity.BaseVmActivity
import com.ld.lib_base.base.fragment.BaseVmFragment
import com.ld.lib_base.base.viewmodel.BaseViewModel
import com.ld.lib_base.bean.home.AriticleResponse
import com.ld.lib_base.ext.util.logd
import com.ld.lib_base.ext.util.loge
import com.ld.lib_base.constant.Constant
import com.ld.lib_base.network.network.ApiResponse
import com.ld.lib_base.network.network.AppException
import com.ld.lib_base.network.network.BaseResponse
import com.ld.lib_base.network.network.ExceptionHandle
import com.ld.lib_base.state.ResultState
import com.ld.lib_base.state.paresException
import com.ld.lib_base.state.paresLoading
import com.ld.lib_base.state.paresResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
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
            LogUtils.e("request")
            if (isShowDialog) resultState.paresLoading(loadingMessage)
            //请求体
            block()
        }.onSuccess {
            LogUtils.e("Success")
            resultState.paresResult(it)
        }.onFailure {
            LogUtils.e("onFailure")
            it.message?.logd()
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
    isShowDialog: Boolean = false,
    loadingMessage: String = Constant.STRING_NET_LOADING
): Job {
    return viewModelScope.launch {
        runCatching {
            LogUtils.e("request")
            if (isShowDialog) resultState.paresLoading(loadingMessage)
            //请求体
            block()
        }.onSuccess {
            LogUtils.e("Success")
            resultState.paresResult(it)
        }.onFailure {
            LogUtils.e("onFailure")
            it.message?.loge()
            resultState.paresException(it)
        }
    }
}


/**
 * 显示页面状态，这里有个技巧，成功回调在第一个，其后两个带默认值的回调可省
 * @param resultState 接口返回值
 * @param onLoading 加载中
 * @param onSuccess 成功回调
 * @param onError 失败回调
 *
 */
fun <T> BaseVmFragment<*>.parseState(
    resultState: ResultState<T>,
    onLoading: ((String) -> Unit)? = null,
    onSuccess: (T) -> Unit,
    onError: ((AppException) -> Unit)? = null
) {
    when (resultState) {
        is ResultState.Loading -> {
            onLoading?.run { this(resultState.loadingMessage) }
        }
        is ResultState.Success -> {
            onSuccess(resultState.data)
        }
        is ResultState.Error -> {
            onError?.run { this(resultState.error) }
        }
    }
}


/**
 * 显示页面状态，这里有个技巧，成功回调在第一个，其后两个带默认值的回调可省
 * @param resultState 接口返回值
 * @param onLoading 加载中
 * @param onSuccess 成功回调
 * @param onError 失败回调
 *
 */
fun <T> BaseVmActivity<*>.parseState(
    resultState: ResultState<T>,
    onLoading: ((String) -> Unit)? = null,
    onSuccess: (T) -> Unit,
    onError: ((AppException) -> Unit)? = null
) {
    when (resultState) {
        is ResultState.Loading -> {
            onLoading?.run { this(resultState.loadingMessage) }
        }
        is ResultState.Success -> {
            onSuccess(resultState.data)
        }
        is ResultState.Error -> {
            onError?.run { this(resultState.error) }
        }
    }
}

/**
 * 过滤服务器结果，失败抛异常
 * @param block 请求体方法，必须要用suspend关键字修饰
 * @param success 成功回调
 * @param error 失败回调 可不传
 * @param isShowDialog 是否显示加载框
 * @param loadingMessage 加载框提示内容
 */
fun <T> BaseViewModel.request(
    block: suspend () -> BaseResponse<T>,
    success: (T) -> Unit,
    error: (AppException) -> Unit = {},
    isShowDialog: Boolean = false,
    loadingMessage: String =  Constant.STRING_NET_LOADING
): Job {
    //如果需要弹窗 通知Activity/fragment弹窗
    return viewModelScope.launch {
        runCatching {
//            if (isShowDialog) loadingChange.showDialog.postValue(loadingMessage)
            //请求体
            block()
        }.onSuccess {
            //网络请求成功 关闭弹窗
//            loadingChange.dismissDialog.postValue(false)
            runCatching {
                //校验请求结果码是否正确，不正确会抛出异常走下面的onFailure
                executeResponse(it) { t -> success(t)
                }
            }.onFailure { e ->
                //打印错误消息
                e.message?.loge()
                //失败回调
                error(ExceptionHandle.handleException(e))
            }
        }.onFailure {
            //网络请求异常 关闭弹窗
//            loadingChange.dismissDialog.postValue(false)
            //打印错误消息
            it.message?.loge()
            //失败回调
            error(ExceptionHandle.handleException(it))
        }
    }
}


/**
 * 请求结果过滤，判断请求服务器请求结果是否成功，不成功则会抛出异常
 */
suspend fun <T> executeResponse(
    response: BaseResponse<T>,
    success: suspend CoroutineScope.(T) -> Unit
) {
    coroutineScope {
        when {
            response.isSuccess() -> {
                success(response.getResponseData())
            }
            else -> {
                throw AppException(
                    response.getResponseCode(),
                    response.getResponseMsg(),
                    response.getResponseMsg()
                )
            }
        }
    }
}