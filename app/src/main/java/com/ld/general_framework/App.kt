package com.ld.general_framework

import androidx.multidex.MultiDex
import com.kingja.loadsir.callback.SuccessCallback
import com.kingja.loadsir.core.LoadSir
import com.ld.lib_widget.widget.loadCallBack.LoadingCallBack
import com.ld.lib_base.base.BaseApp
import com.ld.lib_base.ext.util.jetpackMvvmLog
import com.tencent.mmkv.MMKV
import me.hgj.jetpackmvvm.demo.app.weight.loadCallBack.EmptyCallback
import me.hgj.jetpackmvvm.demo.app.weight.loadCallBack.ErrorCallback
import java.io.File

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
        MultiDex.install(this)

        MMKV.initialize(this.filesDir.absolutePath + File.separator + "mmkv")
        //界面加载管理
        LoadSir.beginBuilder()
            .addCallback(LoadingCallBack())
            .addCallback(ErrorCallback())
            .addCallback(EmptyCallback())
            .setDefaultCallback(SuccessCallback::class.java)
            .commit()

        jetpackMvvmLog = BuildConfig.DEBUG
    }

}