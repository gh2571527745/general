package com.ld.general_framework.event

import com.ld.general_framework.model.bean.CollectBus
import com.ld.lib_base.base.viewmodel.BaseViewModel
import com.ld.lib_base.callback.livedata.event.EventLiveData

/**
 *  author : ld
 *  time   : 2020/12/29
 *  desc   :App全局的ViewModel,可以在这里发送全局通知替换EventBus,LiveDataBus等
 */
class EventViewModel : BaseViewModel() {

    //全局收藏，在任意一个地方收藏或取消收藏，监听该值的界面都会收到消息
    val collectEvent = EventLiveData<CollectBus>()
}