package com.ld.module_other.activity

import android.os.Bundle
import com.blankj.utilcode.util.LogUtils
import com.ld.lib_base.base.activity.BaseActivity
import com.ld.lib_base.base.viewmodel.BaseViewModel
import com.ld.module_other.R
import com.ld.module_other.databinding.ActivityOtherBinding

class OtherActivity : BaseActivity<BaseViewModel, ActivityOtherBinding>() {
    override fun layoutId(): Int = R.layout.activity_other

    override fun initView(savedInstanceState: Bundle?) {
        LogUtils.e("OtherActivity")
    }
}
