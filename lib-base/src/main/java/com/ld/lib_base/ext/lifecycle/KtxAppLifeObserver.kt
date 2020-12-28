package com.ld.lib_base.ext.lifecycle

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.ld.lib_base.callback.livedata.BooleanLiveData

/**
 *  author : ld
 *  time   : 2020/12/28
 *  desc   :前后台
 */
object KtxAppLifeObserver : LifecycleObserver {
    var isForeground = BooleanLiveData()

    //在前台
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private fun onForeground() {
        isForeground.value = true
    }

    //在后台
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private fun onBackground() {
        isForeground.value = false
    }
}