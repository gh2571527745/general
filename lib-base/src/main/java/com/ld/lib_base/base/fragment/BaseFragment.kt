package com.ld.lib_base.base.fragment

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import com.ld.lib_base.base.viewmodel.BaseViewModel
import com.ld.lib_base.event.AppViewModel
import com.ld.lib_base.event.EventViewModel
import com.ld.lib_base.ext.getAppViewModel

/**
 *  author : ld
 *  time   : 2020/12/29
 *  desc   :
 */
abstract class BaseFragment<VM : BaseViewModel, DB : ViewDataBinding> : BaseVmDbFragment<VM, DB>() {

    //Application全局的ViewModel，里面存放了一些账户信息，基本配置信息等
    val appViewModel: AppViewModel by lazy { getAppViewModel<AppViewModel>() }

    //Application全局的ViewModel，用于发送全局通知操作
    val eventViewModel: EventViewModel by lazy { getAppViewModel<EventViewModel>() }

    abstract override fun layoutId(): Int

    /**
     * 懒加载 只有当前fragment视图显示时才会触发该方法
     */
    override fun lazyLoadData() {}

    /**
     * Fragment执行onViewCreated后触发
     */
    override fun initData() {}

    override fun reload() {}

    /**
     * 创建LiveData观察者
     */
    override fun createObserver() {}

    abstract override fun initView(savedInstanceState: Bundle?)
}