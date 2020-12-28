package com.ld.lib_base.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.ld.lib_base.base.viewmodel.BaseViewModel

/**
 *  author : ld
 *  time   : 2020/12/28
 *  desc   :ViewModelFragment基类，自动把ViewModel和Databind注入进来
 *  需要使用DataBind继承
 */
abstract class BaseVmDbFragment<VM : BaseViewModel, DB : ViewDataBinding> : BaseVmFragment<VM>() {
    //该类绑定的ViewDataBingding
    lateinit var mDataBinding: DB
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mDataBinding = DataBindingUtil.inflate(inflater, layoutId(), container, false)
        mDataBinding.lifecycleOwner = this
        return mDataBinding.root
    }
}