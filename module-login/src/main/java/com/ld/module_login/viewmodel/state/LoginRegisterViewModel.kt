package com.ld.module_login.viewmodel.state

import com.ld.lib_base.base.viewmodel.BaseViewModel
import me.hgj.jetpackmvvm.callback.databind.BooleanObservableField
import me.hgj.jetpackmvvm.callback.databind.StringObservableField
import me.hgj.jetpackmvvm.callback.livedata.StringLiveData

/**
 *  author : ld
 *  time   : 2021/01/05
 *  desc   :
 */
class LoginRegisterViewModel : BaseViewModel() {
    //用户名
    var username = StringObservableField()

    //密码
    var password = StringObservableField()

    var password2 = StringObservableField()

    //是否显示明文密码
    var isShowPwd = BooleanObservableField()

    var isShowPwd2 = BooleanObservableField()
}