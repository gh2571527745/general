package com.ld.lib_base.base.application

import android.app.Application

/**
 *  author : ld
 *  time   : 2021/01/08
 *  desc   :
 */
class ModuleLifecycleConfig private constructor() {
    private object SingleHolder {
        val instance = ModuleLifecycleConfig()
    }

    companion object {
        val instance = SingleHolder.instance
    }

    fun initModuleAhead(application: Application) {
        for (moduleName in ModuleLifecycleReflects.initModuleNames) {
            try {
                val clazz = Class.forName(moduleName)
                val init = clazz.newInstance() as IModuleInit
                init.onInitAhead(application)
            } catch (e: ClassNotFoundException) {
                e.printStackTrace()
            } catch (e: InstantiationException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            }
        }
    }

    fun initModuleAfter(application: Application) {
        for (moduleName in ModuleLifecycleReflects.initModuleNames) {
            try {
                val clazz = Class.forName(moduleName)
                val init = clazz.newInstance() as IModuleInit
                // 调用初始化方法
                init.onInitAfter(application)
            } catch (e: ClassNotFoundException) {
                e.printStackTrace()
            } catch (e: InstantiationException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            }
        }
    }

}