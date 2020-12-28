package com.ld.lib_base.ext.lifecycle

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.ld.lib_base.ext.util.logd

/**
 *  author : ld
 *  time   : 2020/12/28
 *  desc   :
 */
class KtxLifeCycleCallBack:Application.ActivityLifecycleCallbacks {
    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        KtxActivityManager.pushActivity(activity)
        "onActivityCreated : ${activity.localClassName}".logd()
    }
    override fun onActivityStarted(activity: Activity) {
        "onActivityStarted : ${activity.localClassName}".logd()
    }

    override fun onActivityResumed(activity: Activity) {
        "onActivityResumed : ${activity.localClassName}".logd()
    }

    override fun onActivityPaused(activity: Activity) {
        "onActivityPaused : ${activity.localClassName}".logd()
    }


    override fun onActivityDestroyed(activity: Activity) {
        "onActivityDestroyed : ${activity.localClassName}".logd()
        KtxActivityManager.popActivity(activity)
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

    }

    override fun onActivityStopped(activity: Activity) {
        "onActivityStopped : ${activity.localClassName}".logd()
    }
}