package com.ld.lib_base.base.activity

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import com.blankj.utilcode.util.ToastUtils
import com.ld.lib_base.base.viewmodel.BaseViewModel
import com.ld.lib_base.event.AppViewModel
import com.ld.lib_base.event.EventViewModel
import com.ld.lib_base.ext.getAppViewModel
import com.ld.lib_base.network.StringConstant
import com.ld.lib_base.network.network.manager.NetState

/**
 *  author : ld
 *  time   : 2020/12/29
 *  desc   :项目基类，实现弹窗等或者加入自己的需求功能
 */
abstract class BaseActivity<VM : BaseViewModel, DB : ViewDataBinding> : BaseVmDbActivity<VM, DB>() {
    //Application 全局的ViewModel,存放账户信息、基本配置信息等
    val appViewModel: AppViewModel by lazy { getAppViewModel<AppViewModel>() }

    //Application 全局的ViewModel,用于发送全局通知
    val eventViewModel: EventViewModel by lazy { getAppViewModel<EventViewModel>() }

    abstract override fun layoutId(): Int

    override fun createObserver() {}

    abstract override fun initView(savedInstanceState: Bundle?)

    override fun onNetworkStateChanged(it: NetState) {
        super.onNetworkStateChanged(it)
        if (it.isSuccess) {
            if (mTipView != null && mTipView.parent != null) {
                mWindowManager.removeView(mTipView)
            }
        } else {
            if (mTipView.parent == null) {
                mWindowManager.addView(mTipView, mLayoutParams)
            }
        }
    }
}