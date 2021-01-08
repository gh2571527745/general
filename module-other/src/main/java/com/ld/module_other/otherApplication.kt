package com.ld.module_other

import com.ld.lib_base.base.BaseApp
import com.ld.lib_base.base.application.ModuleLifecycleConfig

/**
 *  author : ld
 *  time   : 2021/01/08
 *  desc   :
 */
class otherApplication : BaseApp() {
    override fun onCreate() {
        super.onCreate()
        ModuleLifecycleConfig.instance.initModuleAhead(this)
    }
}