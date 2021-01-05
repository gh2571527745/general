package com.ld.general_framework.ui.fragment

import android.os.Bundle
import com.blankj.utilcode.util.LogUtils
import com.ld.general_framework.R
import com.ld.lib_base.base.fragment.BaseFragment
import com.ld.general_framework.databinding.FragmentMainBinding
import com.ld.general_framework.ext.init
import com.ld.general_framework.ext.initMain
import com.ld.general_framework.ext.interceptLongClick
import com.ld.lib_base.base.viewmodel.BaseViewModel
import kotlinx.android.synthetic.main.fragment_main.*

/**
 *  author : ld
 *  time   : 2020/12/29
 *  desc   :
 */
class MainFragment : BaseFragment<BaseViewModel, FragmentMainBinding>() {
    override fun layoutId(): Int = R.layout.fragment_main

    override fun initView(savedInstanceState: Bundle?) {
        LogUtils.e("MainFragment")
        mainViewpager.initMain(this)
        mainBottom.init{
            when (it) {
                R.id.menu_main -> mainViewpager.setCurrentItem(0, false)
                R.id.menu_other -> mainViewpager.setCurrentItem(1, false)
            }
        }
        mainBottom.interceptLongClick(R.id.menu_main,R.id.menu_other)
    }

}