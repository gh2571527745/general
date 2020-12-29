package com.ld.general_framework.base

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import com.ld.lib_base.base.fragment.BaseVmDbFragment
import com.ld.lib_base.base.viewmodel.BaseViewModel
import com.ld.lib_base.network.network.manager.NetState

/**
 *  author : ld
 *  time   : 2020/12/29
 *  desc   :
 */
abstract class BaseFragment<VM : BaseViewModel, DB : ViewDataBinding> : BaseVmDbFragment<VM, DB>() {
    abstract override fun layoutId(): Int

    /**
     * 懒加载 只有当前fragment视图显示时才会触发该方法
     */
    override fun lazyLoadData() {}

    /**
     * Fragment执行onViewCreated后触发
     */
    override fun initData() {}

    /**
     * 创建LiveData观察者
     */
    override fun createObserver() {}

    abstract override fun initView(savedInstanceState: Bundle?)
}