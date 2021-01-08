package com.ld.lib_base.base.application

import android.app.Application

/**
 *  author : ld
 *  time   : 2021/01/08
 *  desc   :动态配置组件Application,有需要初始化的组件实现该接口,统一在宿主app 的Application进行初始化
 */
interface IModuleInit {
    /**
     * 需要优先初始化
     */
    fun onInitAhead(application: Application): Boolean

    /**
     * 可以稍后初始化
     */
    fun onInitAfter(application: Application): Boolean
}