package com.ld.module_other.fragment

import android.content.Intent
import android.os.Bundle
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.KeyboardUtils
import com.blankj.utilcode.util.LogUtils
import com.ld.lib_base.base.fragment.BaseFragment
import com.ld.lib_base.base.viewmodel.BaseViewModel
import com.ld.lib_base.ext.nav
import com.ld.lib_base.ext.navigateAction
import com.ld.module_other.R
import com.ld.module_other.activity.LoginActivity
import com.ld.module_other.databinding.FragmentOtherBinding
import kotlinx.android.synthetic.main.fragment_other.*

/**
 *  author : ld
 *  time   : 2021/01/05
 *  desc   :
 */
class OtherFragment : BaseFragment<BaseViewModel, FragmentOtherBinding>() {
    override fun layoutId(): Int = R.layout.fragment_other

    override fun initView(savedInstanceState: Bundle?) {
        LogUtils.e("OtherFragment")
        mDataBinding.click = ProxyClick()
    }

    override fun lazyLoadData() {

    }

    inner class ProxyClick {
        fun goLogin() {
            LogUtils.e("登录")
            ActivityUtils.startActivity(Intent(context, LoginActivity::class.java))
        }
    }
}