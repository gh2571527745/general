package com.ld.lib_base.base.application

import android.app.Application
import androidx.multidex.MultiDex
import com.alibaba.android.arouter.launcher.ARouter
import com.kingja.loadsir.callback.SuccessCallback
import com.kingja.loadsir.core.LoadSir
import com.ld.lib_base.BuildConfig
import com.ld.lib_base.ext.util.jetpackMvvmLog
import com.ld.lib_db.manager.DbUtil
import com.ld.lib_widget.widget.loadCallBack.LoadingCallback
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.tencent.mmkv.MMKV
import me.hgj.jetpackmvvm.demo.app.weight.loadCallBack.EmptyCallback
import me.hgj.jetpackmvvm.demo.app.weight.loadCallBack.ErrorCallback
import java.io.File

/**
 *  author : ld
 *  time   : 2021/01/08
 *  desc   :
 */
class CommonModuleInit : IModuleInit {
    override fun onInitAhead(application: Application): Boolean {

        SmartRefreshLayout.setDefaultRefreshHeaderCreator { _, _ ->
            ClassicsHeader(
                application
            )
        }
        SmartRefreshLayout.setDefaultRefreshFooterCreator { _, _ ->
            ClassicsFooter(
                application
            )
        }

        MultiDex.install(application)

        MMKV.initialize(application.filesDir.absolutePath + File.separator + "mmkv")
        //界面加载管理
        LoadSir.beginBuilder()
            .addCallback(LoadingCallback())
            .addCallback(ErrorCallback())
            .addCallback(EmptyCallback())
            .setDefaultCallback(SuccessCallback::class.java)
            .commit()

        ARouter.openLog()
        ARouter.openDebug()
        ARouter.init(application)

        jetpackMvvmLog = BuildConfig.DEBUG

        DbUtil.instance.init(application, "wanandroid")


        return false
    }

    override fun onInitAfter(application: Application): Boolean {
        return false
    }
}