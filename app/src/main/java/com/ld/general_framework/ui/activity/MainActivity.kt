package com.ld.general_framework.ui.activity

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.navigation.Navigation
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.ld.general_framework.R
import com.ld.general_framework.databinding.ActivityMainBinding
import com.ld.lib_base.base.activity.BaseActivity
import com.ld.lib_base.base.viewmodel.BaseViewModel
import com.ld.lib_base.constant.RouterActivityPath
import com.ld.lib_base.network.StringConstant
import com.ld.lib_base.network.network.manager.NetState

@Route(path = RouterActivityPath.Main.PAGE_MAIN)
class MainActivity : BaseActivity<BaseViewModel, ActivityMainBinding>() {
    override fun layoutId(): Int = R.layout.activity_home

    var exitTime = 0L
    override fun initView(savedInstanceState: Bundle?) {
        LogUtils.e("MainActivity")
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val nav = Navigation.findNavController(this@MainActivity, R.id.fragment_host)
                if (nav.currentDestination != null && nav.currentDestination!!.id != R.id.mainFragment) {
                    //如果当前界面不是主页，那么直接调用返回即可
                    nav.navigateUp()
                } else {
                    //是主页
                    if (System.currentTimeMillis() - exitTime > 2000) {
                        ToastUtils.showShort("再按一次退出程序")
                        exitTime = System.currentTimeMillis()
                    } else {
                        finish()
                    }
                }
            }
        })
    }

    override fun onNetworkStateChanged(it: NetState) {
        super.onNetworkStateChanged(it)
        if (it.isSuccess) {
            ToastUtils.showShort(StringConstant.STRING_NET_HAS_NET)
        } else {
            ToastUtils.showShort(StringConstant.STRING_NET_HAS_NET_NO)
        }
    }
}
