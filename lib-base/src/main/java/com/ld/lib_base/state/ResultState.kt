package com.ld.lib_base.state

import androidx.lifecycle.MutableLiveData
import com.ld.lib_base.network.network.AppException
import com.ld.lib_base.network.network.BaseResponse
import com.ld.lib_base.network.network.ExceptionHandle
import java.lang.Error

/**
 *  author : ld
 *  time   : 2020/12/28
 *  desc   :自定义结果集封装类
 */
sealed class ResultState<out T> {
    companion object {
        fun <T> onAppSuccess(data: T): ResultState<T> = Success(data)
        fun <T> onAppLoading(loadingMessage: String): ResultState<T> = Loading(loadingMessage)
        fun <T> onAppError(error: AppException): ResultState<T> = Error(error)
    }

    data class Loading(val loadingMessage: String) : ResultState<Nothing>()
    data class Success<out T>(val data: T) : ResultState<T>()
    data class Error(val error: AppException) : ResultState<Nothing>()
}

/**
 * 处理返回值
 * @param result 请求结果
 */
fun <T> MutableLiveData<ResultState<T>>.paresResult(result: BaseResponse<T>) {
    value = when {
        result.isSuccess() -> {
            ResultState.onAppSuccess(result.getResponseData())
        }
        else -> {
            ResultState.onAppError(AppException(result.getResponseCode(), result.getResponseMsg()))
        }
    }
}

/**
 * 不处理返回值 直接返回请求结果
 * @param result 请求结果
 */
fun <T> MutableLiveData<ResultState<T>>.paresResult(result: T) {
    value = ResultState.onAppSuccess(result)
}

fun <T> MutableLiveData<ResultState<T>>.paresResult(e: Throwable) {
    value = ResultState.onAppError(ExceptionHandle.handleException(e))
}