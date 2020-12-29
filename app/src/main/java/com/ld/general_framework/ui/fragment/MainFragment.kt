package com.ld.general_framework.ui.fragment

import android.os.Bundle
import com.ld.general_framework.R
import com.ld.general_framework.base.BaseFragment
import com.ld.general_framework.databinding.FragmentMainBinding
import com.ld.lib_base.base.viewmodel.BaseViewModel

/**
 *  author : ld
 *  time   : 2020/12/29
 *  desc   :
 */
class MainFragment : BaseFragment<BaseViewModel, FragmentMainBinding>() {
    override fun layoutId(): Int = R.layout.fragment_main

    override fun initView(savedInstanceState: Bundle?) {
    }

}