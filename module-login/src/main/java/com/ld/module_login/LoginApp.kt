package com.ld.module_login

import com.ld.lib_base.base.BaseApp
import com.ld.lib_base.base.application.ModuleLifecycleConfig

/**
 *  author : ld
 *  time   : 2021/01/14
 *  desc   :
 */
class LoginApp:BaseApp() {
    override fun onCreate() {
        super.onCreate()
        ModuleLifecycleConfig.instance.initModuleAhead(this)
    }
}