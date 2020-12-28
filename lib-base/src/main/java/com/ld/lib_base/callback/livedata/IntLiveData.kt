package me.hgj.jetpackmvvm.callback.livedata

import androidx.lifecycle.MutableLiveData

/**
 *  author : ld
 *  time   : 2020/12/28
 *  desc   :自定义的Boolean 类型 MutableLiveData 提供了默认值，避免取值的时候还要判空
 */
class IntLiveData : MutableLiveData<Int>() {

    override fun getValue(): Int {
        return super.getValue() ?: 0
    }
}