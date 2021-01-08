package com.ld.lib_base.base.application

/**
 *  author : ld
 *  time   : 2021/01/08
 *  desc   :
 */
object ModuleLifecycleReflects {

    private const val baseInit = "com.ld.lib_base.base.application.CommonModuleInit"

    @JvmField
    var initModuleNames = arrayOf(baseInit)
}