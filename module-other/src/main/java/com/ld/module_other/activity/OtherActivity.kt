package com.ld.module_other.activity

import android.os.Bundle
import androidx.lifecycle.Observer
import com.blankj.utilcode.util.LogUtils
import com.ld.lib_base.api.coroutine.LoginApi
import com.ld.lib_base.api.retrofit.LoginApiRetrofit
import com.ld.lib_base.base.activity.BaseActivity
import com.ld.lib_base.base.viewmodel.BaseViewModel
import com.ld.lib_base.network.retrofit.RetrofitClient
import com.ld.lib_base.util.CacheUtil
import com.ld.module_other.R
import com.ld.module_other.databinding.ActivityOtherBinding

class OtherActivity : BaseActivity<BaseViewModel, ActivityOtherBinding>() {
    override fun layoutId(): Int = R.layout.activity_other

    override fun initView(savedInstanceState: Bundle?) {
        LogUtils.e("OtherActivity")

//        RetrofitClient.instance.create(LoginApiRetrofit.SERVER_URL, LoginApiRetrofit::class.java)
//            .loginNotCoroutine("wa_chumen", "123456").observe(this,
//                Observer {
//                    CacheUtil.setUser(it.data)
//                    appViewModel.userinfo.value = it.data
//                })
    }
}
