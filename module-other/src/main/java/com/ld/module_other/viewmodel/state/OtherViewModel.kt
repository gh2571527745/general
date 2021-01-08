package com.ld.module_other.viewmodel.state

import com.ld.lib_base.base.viewmodel.BaseViewModel
import me.hgj.jetpackmvvm.callback.databind.StringObservableField

/**
 *  author : ld
 *  time   : 2021/01/08
 *  desc   :
 */
class OtherViewModel : BaseViewModel() {
    var name = StringObservableField("请先登录")
}