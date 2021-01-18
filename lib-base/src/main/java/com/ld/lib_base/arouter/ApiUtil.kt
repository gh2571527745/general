package com.ld.lib_base.arouter

import com.ld.lib_base.api.coroutine.HomeApi
import com.ld.lib_base.api.coroutine.LoginApi
import com.ld.lib_base.api.coroutine.OtherApi
import com.ld.lib_base.network.network.NetworkApi

/**
 *  author : ld
 *  time   : 2021/01/07
 *  desc   :Arouter的工具类(区分不同的模块Api和基础连接)
 */

fun otherApi(): OtherApi {
    return NetworkApi.INSTANCE.getApi(OtherApi::class.java, OtherApi.SERVER_URL)
}

fun homeApi(): HomeApi {
    return NetworkApi.INSTANCE.getApi(HomeApi::class.java, HomeApi.SERVER_URL)
}

fun loginApi(): LoginApi {
    return NetworkApi.INSTANCE.getApi(LoginApi::class.java, LoginApi.SERVER_URL)
}