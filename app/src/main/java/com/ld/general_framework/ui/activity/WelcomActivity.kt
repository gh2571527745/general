package com.ld.general_framework.ui.activity

import android.content.Intent
import android.os.Bundle
import com.blankj.utilcode.util.LogUtils
import com.ld.general_framework.R
import com.ld.general_framework.databinding.ActivityWelcomeBinding
import com.ld.general_framework.ext.lanchMain
import com.ld.lib_base.base.activity.BaseActivity
import com.ld.lib_base.base.viewmodel.BaseViewModel
import com.ld.lib_base.util.CacheUtil

/**
 *  author : ld
 *  time   : 2020/12/29
 *  desc   :
 */
class WelcomActivity : BaseActivity<BaseViewModel, ActivityWelcomeBinding>() {
    override fun layoutId(): Int = R.layout.activity_welcome

    override fun initView(savedInstanceState: Bundle?) {
        LogUtils.e("initView")
        //防止出现按Home键回到桌面时，再次点击重新进入该界面bug
        if (intent.flags and Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT !== 0) {
            finish()
            return
        }
        mDatabing.click = ProxyClick()
        if (CacheUtil.isFirst()) {
            //第一次打开App
        } else {
            ProxyClick().toMain()
        }
    }

    inner class ProxyClick {
        fun toMain() {
            LogUtils.e("toMain")
            CacheUtil.setFirst(false)

            lanchMain()

            finish()
            //带点渐变动画
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
    }


}