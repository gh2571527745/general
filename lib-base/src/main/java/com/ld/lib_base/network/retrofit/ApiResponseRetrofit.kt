package com.ld.lib_base.network.retrofit

import com.ld.lib_base.constant.Constant

/**
 *  author : ld
 *  time   : 2021/01/15
 *  desc   :
 */
class ApiResponseRetrofit<T> {

    //状态码
    var errorCode: Int
    var errorMsg: String
    var data: T?

    companion object {
        const val codeSuccess = 0
        const val codeError = 1
    }

    constructor(code: Int, msg: String) {
        errorCode = code
        errorMsg = msg
        data = null
    }

    constructor(code: Int, msg: String, data: T) {
        errorCode = code
        errorMsg = msg
        this.data = data
    }

    val isSuccess: Boolean
        get() = if (errorCode == Constant.NET_DATA_GET_SUCCESS) {
            true
        } else {
            false
        }

}