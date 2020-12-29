package me.hgj.jetpackmvvm.demo.app.weight.loadCallBack

import com.kingja.loadsir.callback.Callback
import com.ld.general_framework.R


class ErrorCallback : Callback() {

    override fun onCreateView(): Int {
        return R.layout.layout_error
    }

}