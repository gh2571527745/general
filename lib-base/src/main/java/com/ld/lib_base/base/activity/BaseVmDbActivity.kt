package com.ld.lib_base.base.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.ld.lib_base.base.viewmodel.BaseViewModel

/**
 *  author : ld
 *  time   : 2020/12/25
 *  desc   :需要使用Databing的继承
 */
abstract class BaseVmDbActivity<VM : BaseViewModel, DB : ViewDataBinding> : BaseVmActivity<VM>() {
    lateinit var mDatabing: DB
    override fun onCreate(savedInstanceState: Bundle?) {
        userDataBinding(true)
        super.onCreate(savedInstanceState)
    }

    override fun initDataBind() {
        mDatabing = DataBindingUtil.setContentView(this, layoutId())
        mDatabing.lifecycleOwner = this
    }

}