package com.ld.module_home

import android.app.Application
import com.ld.lib_base.base.BaseApp
import com.ld.lib_base.base.application.ModuleLifecycleConfig

class HomeApp : BaseApp() {
    override fun onCreate() {
        super.onCreate()
        ModuleLifecycleConfig.instance.initModuleAhead(this)
    }
}