package com.ld.lib_base.base.viewmodel

import androidx.lifecycle.ViewModel
import com.ld.lib_base.callback.livedata.event.EventLiveData

/**
 *  author : ld
 *  time   : 2020/12/21
 *  desc   :ViewModel的基类
 */
class BaseViewModel : ViewModel() {
    val loading: UiLoading by lazy { UiLoading() }

    inner class UiLoading {
        //显示加载中
        val showDialog by lazy { EventLiveData<String>() }

        //隐藏
        val diamissDialog by lazy { EventLiveData<Boolean>() }
    }
}