package com.ld.general_framework.ui.activity

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.LogUtils
import com.ld.general_framework.R
import com.ld.general_framework.databinding.ActivityMainBinding
import com.ld.lib_base.base.activity.BaseActivity
import com.ld.lib_base.base.viewmodel.BaseViewModel
import com.ld.lib_base.arouter.RouterActivityPath

@Route(path = RouterActivityPath.Main.PAGE_MAIN)
class MainActivity : BaseActivity<BaseViewModel, ActivityMainBinding>() {
    override fun layoutId(): Int = R.layout.activity_main

    override fun initView(savedInstanceState: Bundle?) {
        LogUtils.e("MainActivity")
    }
}
