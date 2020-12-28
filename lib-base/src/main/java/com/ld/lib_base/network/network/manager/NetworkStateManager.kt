package com.ld.lib_base.network.network.manager

import com.ld.lib_base.callback.livedata.event.EventLiveData

/**
 *  author : ld
 *  time   : 2020/12/25
 *  desc   :
 */
class NetworkStateManager private constructor() {
    val mNetworkStateCallback = EventLiveData<NetState>()

    companion object {
        val instance: NetworkStateManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            NetworkStateManager()
        }
    }
}