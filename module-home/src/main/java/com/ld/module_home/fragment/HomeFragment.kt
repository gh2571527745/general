package com.ld.module_home.fragment

import android.os.Bundle
import com.ld.lib_base.base.fragment.BaseFragment
import com.ld.lib_base.base.viewmodel.BaseViewModel
import com.ld.module_home.R
import com.ld.module_home.databinding.FragmentHomeBinding

/**
 *  author : ld
 *  time   : 2021/01/04
 *  desc   :
 */
class HomeFragment : BaseFragment<BaseViewModel, FragmentHomeBinding>() {
    override fun layoutId(): Int = R.layout.fragment_home

    override fun initView(savedInstanceState: Bundle?) {

    }

    override fun lazyLoadData() {
        
    }
}