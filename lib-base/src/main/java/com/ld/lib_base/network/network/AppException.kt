package com.ld.lib_base.network.network

import java.lang.Exception

/**
 *  author : ld
 *  time   : 2020/12/28
 *  desc   :
 */
class AppException : Exception {
    var errorMsg: String//错误消息
    var errCode: Int = 0//错误码
    var errorLog: String?//错误日志

    constructor(errCode: Int, error: String?, errorLog: String? = "") : super(error) {
        this.errorMsg = error?:"请求失败，请稍后重试"
        this.errCode = errCode
        this.errorLog = errorLog?:this.errorMsg
    }
    constructor(error:Error,e:Throwable?){
        errCode = error.getKey()
        errorMsg = error.getValue()
        errorLog = e?.message
    }
}