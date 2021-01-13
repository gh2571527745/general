package com.ld.module_other.activity

import android.os.Bundle
import androidx.lifecycle.Observer
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.ld.lib_base.base.activity.BaseActivity
import com.ld.lib_base.base.viewmodel.BaseViewModel
import com.ld.module_other.R
import com.ld.module_other.databinding.ActivityOtherBinding

class OtherActivity : BaseActivity<BaseViewModel, ActivityOtherBinding>() {
    override fun layoutId(): Int = R.layout.activity_other

    override fun initView(savedInstanceState: Bundle?) {
        LogUtils.e("OtherActivity")
    }

    override fun createObserver() {
//        appViewModel.run {
//            userinfo.observeInActivity(this@OtherActivity, Observer {
//                if (it != null) {
//                    ToastUtils.showShort(userinfo.value?.username)
//                } else {
//                }
//            })
//        }
    }
}
