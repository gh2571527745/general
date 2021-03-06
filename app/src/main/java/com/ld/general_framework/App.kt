package com.ld.general_framework

import com.ld.lib_base.base.BaseApp
import com.ld.lib_base.base.application.ModuleLifecycleConfig

/**
 *  author : ld
 *  time   : 2020/12/29
 *  desc   :
 */
class App : BaseApp() {
    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        ModuleLifecycleConfig.instance.initModuleAhead(this)
    }

}