package com.ld.module_other.activity

import android.os.Bundle
import android.text.InputType
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.blankj.utilcode.util.KeyboardUtils
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.ld.lib_base.base.activity.BaseActivity
import com.ld.lib_base.ext.loadServiceInit
import com.ld.lib_base.ext.parseState
import com.ld.lib_base.ext.showError
import com.ld.lib_base.ext.showLoading
import com.ld.lib_base.util.CacheUtil
import com.ld.module_other.R
import com.ld.module_other.databinding.ActivityLoginBinding
import com.ld.module_other.viewmodel.request.RequestLoginRegisterViewModel
import com.ld.module_other.viewmodel.state.LoginRegisterViewModel
import kotlinx.android.synthetic.main.activity_login.*


/**
 *  author : ld
 *  time   : 2021/01/05
 *  desc   :
 */
class LoginActivity : BaseActivity<LoginRegisterViewModel, ActivityLoginBinding>() {
    override fun layoutId(): Int = R.layout.activity_login

    private val requestLoginRegisterViewModel: RequestLoginRegisterViewModel by viewModels()

    override fun initView(savedInstanceState: Bundle?) {
        LogUtils.e("LoginFragment")
        loadsir = loadServiceInit(ll_login) {
            requestLoginRegisterViewModel.loginReq(
                mViewModel.username.get(),
                mViewModel.password.get()
            )
        }

        mDatabing.viewmodel = mViewModel
        mDatabing.click = ProxyClick()

//        cb_login.setOnCheckedChangeListener { buttonView, isChecked ->
//            mViewModel.isShowPwd.set(isChecked)
//            if (isChecked) {
//                //选择状态 显示明文
//                et_login.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD)
//            } else {
//                //默认状态显示密码--设置文本 要一起写才能起作用
//                et_login.setInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD)
//            }
//        }
    }

    override fun createObserver() {
        requestLoginRegisterViewModel.loginResult.observe(this, Observer { resultState ->
            parseState(resultState, {
                loadsir.showLoading()
            }, {
                loadsir.showSuccess()
            }, {
                if (it.errCode == -1) {//错误码错误
                    ToastUtils.showShort(it.errorMsg)
                    loadsir.showSuccess()
                } else {//Error错误
                    loadsir.showError()
                }
            })
        })
        requestLoginRegisterViewModel.loginResult2.observe(this, Observer { resultState ->
            parseState(resultState, {
                loadsir.showLoading()
            }, {
                if (it.errorCode == -1) {//错误码错误
                    ToastUtils.showShort(it.errorMsg)
                    return@parseState
                }

                loadsir.showSuccess()

                CacheUtil.setUser(it.data)
                CacheUtil.setIsLogin(true)
                appViewModel.userinfo.value = it.data

                finish()
            }, {//Error错误
                loadsir.showError()
            })
        }
        )

    }

    inner class ProxyClick {
        fun clear() {
            mViewModel.username.set("")
        }

        fun login() {
            when {
                mViewModel.username.get().isEmpty() -> ToastUtils.showShort("请填写账号")
                mViewModel.password.get().isEmpty() -> ToastUtils.showShort("请填写密码")
                else -> {
                    LogUtils.e("登录")
                    requestLoginRegisterViewModel.loginReq(
                        mViewModel.username.get(),
                        mViewModel.password.get()
                    )
                }
            }
        }

        fun goRegister() {
            KeyboardUtils.hideSoftInput(this@LoginActivity)
        }
    }
}