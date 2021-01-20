package com.ld.lib_base.network.network

import com.ld.lib_base.constant.Constant


/**
 *  author : ld
 *  time   : 2021/01/07
 *  desc   :
 */
data class ApiResponse<T>(val errorCode: Int, val errorMsg: String, val data: T) :
    BaseResponse<T>() {
    override fun isSuccess() = errorCode == Constant.NET_DATA_GET_SUCCESS
    override fun getResponseData() = data

    override fun getResponseCode() = errorCode

    override fun getResponseMsg() = errorMsg
}