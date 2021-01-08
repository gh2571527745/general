package com.ld.module_other.fragment

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.LogUtils
import com.ld.lib_base.base.fragment.BaseFragment
import com.ld.module_other.R
import com.ld.module_other.activity.LoginActivity
import com.ld.module_other.databinding.FragmentOtherBinding
import com.ld.module_other.viewmodel.state.OtherViewModel

/**
 *  author : ld
 *  time   : 2021/01/05
 *  desc   :
 */
class OtherFragment : BaseFragment<OtherViewModel, FragmentOtherBinding>() {
    override fun layoutId(): Int = R.layout.fragment_other

    override fun initView(savedInstanceState: Bundle?) {
        LogUtils.e("OtherFragment")
        mDataBinding.vm = mViewModel
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

    override fun createObserver() {
        appViewModel.run {
            userinfo.observeInFragment(this@OtherFragment, Observer {
                if (it != null) {
                    mViewModel.name.set(it.nickname)
                } else {
                    mViewModel.name.set("请先登录")
                }
            })
        }
    }
}