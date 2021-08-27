package com.ld.module_login.viewmodel.request

import androidx.lifecycle.MutableLiveData
import com.ld.lib_base.arouter.loginApi
import com.ld.lib_base.base.viewmodel.BaseViewModel
import com.ld.lib_base.state.ResultState
import com.ld.lib_base.bean.login.UserInfoBean
import com.ld.lib_base.ext.request
import com.ld.lib_base.ext.requestNoCheck
import com.ld.lib_base.network.network.ApiResponse

/**
 *  author : ld
 *  time   : 2021/01/07
 *  desc   :
 */
class RequestLoginRegisterViewModel : BaseViewModel() {

    //方式1 自动脱壳过滤处理请求结果，判断是否成功
    var loginResult = MutableLiveData<ResultState<UserInfoBean>>()

    //方式2 不用框架脱壳，判断是否成功
    var loginResult2 = MutableLiveData<ResultState<ApiResponse<UserInfoBean>>>()

    fun loginReq(username: String, password: String) {
        //方式1
//        request({ loginApi().login(username,password)},loginResult,true)
        //方式2
        requestNoCheck({ loginApi().login(username, password) }, loginResult2, true)
    }
}