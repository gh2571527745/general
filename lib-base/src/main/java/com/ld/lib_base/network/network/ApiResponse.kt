package com.ld.lib_base.network.network


/**
 *  author : ld
 *  time   : 2021/01/07
 *  desc   :
 */
data class ApiResponse<T>(val errorCode: Int, val errorMsg: String, val data: T) :
    BaseResponse<T>() {
    override fun isSuccess() = errorCode == 0
    override fun getResponseData() = data

    override fun getResponseCode() = errorCode

    override fun getResponseMsg() = errorMsg
}