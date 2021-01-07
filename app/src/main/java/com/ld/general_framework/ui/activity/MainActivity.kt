package com.ld.general_framework.ui.activity

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.navigation.Navigation
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.ld.general_framework.R
import com.ld.general_framework.databinding.ActivityMainBinding
import com.ld.lib_base.base.activity.BaseActivity
import com.ld.lib_base.base.viewmodel.BaseViewModel
import com.ld.lib_base.constant.RouterActivityPath
import com.ld.lib_base.network.StringConstant
import com.ld.lib_base.network.network.manager.NetState

@Route(path = RouterActivityPath.Main.PAGE_MAIN)
class MainActivity : BaseActivity<BaseViewModel, ActivityMainBinding>() {
    override fun layoutId(): Int = R.layout.activity_main

    override fun initView(savedInstanceState: Bundle?) {
        LogUtils.e("MainActivity")
    }
}
