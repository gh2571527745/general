package com.ld.lib_base.base.viewmodel

import androidx.lifecycle.ViewModel
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir
import com.ld.lib_base.callback.livedata.event.EventLiveData

/**
 *  author : ld
 *  time   : 2020/12/21
 *  desc   :ViewModel的基类
 */
open class BaseViewModel : ViewModel() {
    var loadsir: LoadService<Any>

    init {
        loadsir = LoadSir.getDefault().register(this) {
            //TODO 点击重试后的操作
            reLoad()
        }
    }

    fun reLoad() {}
}