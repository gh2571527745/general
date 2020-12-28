package com.ld.lib_base.network.network.manager

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager

/**
 *  author : ld
 *  time   : 2020/12/28
 *  desc   :网络变化的监听器
 */
class NetworkStateReceive : BroadcastReceiver() {
    var isInit = true
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == ConnectivityManager.EXTRA_NO_CONNECTIVITY) {
            if (!isInit) {
                if (!NetworkUtil.isNetworkAvailable(context)) {
                    //收到没有网络时先判断之前的值是不是有网络，如果有网络才提示通知，防止重复通知
                    NetworkStateManager.instance.mNetworkStateCallback.value?.let {
                        if (it.isSuccess) {
                            //没网
                            NetworkStateManager.instance.mNetworkStateCallback.value =
                                NetState(isSuccess = false)
                        }
                        return
                    }
                    NetworkStateManager.instance.mNetworkStateCallback.value =
                        NetState(isSuccess = false)
                } else {
                    NetworkStateManager.instance.mNetworkStateCallback.value?.let {
                        if (!it.isSuccess) {
                            //有网络了
                            NetworkStateManager.instance.mNetworkStateCallback.value =
                                NetState(isSuccess = true)
                        }
                        return
                    }
                    NetworkStateManager.instance.mNetworkStateCallback.value =
                        NetState(isSuccess = true)
                }
            }
            isInit = false
        }


    }
}