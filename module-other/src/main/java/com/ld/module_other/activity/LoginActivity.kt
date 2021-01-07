package com.ld.module_other.activity

import android.os.Bundle
import android.widget.CompoundButton
import com.blankj.utilcode.util.KeyboardUtils
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.ld.lib_base.base.activity.BaseActivity
import com.ld.module_other.R
import com.ld.module_other.databinding.ActivityLoginBinding
import com.ld.module_other.viewmodel.LoginRegisterViewModel

/**
 *  author : ld
 *  time   : 2021/01/05
 *  desc   :
 */
class LoginActivity : BaseActivity<LoginRegisterViewModel, ActivityLoginBinding>() {
    override fun layoutId(): Int = R.layout.activity_login

    override fun initView(savedInstanceState: Bundle?) {
        LogUtils.e("LoginFragment")
    }

    inner class ProxyClick {
        fun clear() {
            mViewModel.username.value = ""
        }

        fun login() {
            when {
                mViewModel.username.value.isEmpty() -> ToastUtils.showShort("请填写账号")
                mViewModel.password.get().isEmpty() -> ToastUtils.showShort("请填写密码")

                else -> {
                    LogUtils.e("登录")
                }
            }
        }

        fun goRegister() {
            KeyboardUtils.hideSoftInput(this@LoginActivity)

        }

        var onCheckedChangeListener =
            CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
                mViewModel.isShowPwd.set(isChecked)
            }
    }
}