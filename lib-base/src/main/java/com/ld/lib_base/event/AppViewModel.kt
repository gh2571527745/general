package com.ld.lib_base.event

import com.kunminx.architecture.ui.callback.UnPeekLiveData
import com.ld.lib_base.base.viewmodel.BaseViewModel
import com.ld.lib_base.bean.UserInfo
import com.ld.lib_base.util.CacheUtil

/**
 *  author : ld
 *  time   : 2020/12/29
 *  desc   :App全局的ViewModel,可以存放公共数据，当数据变化时，所有监听他的地方都会受到回调
 *  比如：全局使用的位置信息，账户信息，App的基本配置
 */
class AppViewModel : BaseViewModel() {
    //app的账户信息
    var userinfo = UnPeekLiveData<UserInfo>()

    init {
        //默认保存的账户信息，没有登录过则为null
        userinfo.value = CacheUtil.getUser()
    }

}