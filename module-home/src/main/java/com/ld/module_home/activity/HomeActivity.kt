package com.ld.module_home.activity

import android.os.Bundle
import com.ld.lib_base.base.activity.BaseActivity
import com.ld.lib_base.base.viewmodel.BaseViewModel
import com.ld.module_home.R
import com.ld.module_home.databinding.ActivityHomeBinding


/**
 *  author : ld
 *  time   : 2021/01/05
 *  desc   :首页
 */
class HomeActivity : BaseActivity<BaseViewModel, ActivityHomeBinding>() {
    override fun layoutId(): Int = R.layout.activity_home

    override fun initView(savedInstanceState: Bundle?) {
    }

}
