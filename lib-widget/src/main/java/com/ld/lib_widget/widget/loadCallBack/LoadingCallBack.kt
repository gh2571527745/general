package com.ld.lib_widget.widget.loadCallBack

import android.content.Context
import android.view.View
import com.kingja.loadsir.callback.Callback
import com.ld.lib_widget.R

/**
 *  author : ld
 *  time   : 2020/12/29
 *  desc   :
 */
class LoadingCallBack : Callback() {
    override fun onCreateView(): Int {
        return R.layout.layout_loading
    }

    override fun onReloadEvent(context: Context?, view: View?): Boolean {
        return true
    }
}