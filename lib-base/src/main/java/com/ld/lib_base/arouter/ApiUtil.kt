package com.ld.lib_base.arouter

import com.ld.lib_base.api.OtherApi
import com.ld.lib_base.network.network.NetworkApi

/**
 *  author : ld
 *  time   : 2021/01/07
 *  desc   :
 */

fun otherApi(): OtherApi {
    return NetworkApi.INSTANCE.getApi(OtherApi::class.java, OtherApi.SERVER_URL)
}