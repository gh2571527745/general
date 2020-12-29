package com.ld.general_framework.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ld.general_framework.R
import com.ld.general_framework.base.BaseActivity
import com.ld.general_framework.databinding.ActivityWelcomeBinding
import com.ld.general_framework.util.CacheUtil
import com.ld.lib_base.base.viewmodel.BaseViewModel
import com.ld.lib_base.network.network.manager.NetState

/**
 *  author : ld
 *  time   : 2020/12/29
 *  desc   :
 */
class WelcomActivity : BaseActivity<BaseViewModel, ActivityWelcomeBinding>() {
    override fun layoutId(): Int = R.layout.activity_welcome

    override fun initView(savedInstanceState: Bundle?) {
    }

    inner class ProxClick {
        fun toMain() {
            CacheUtil.setFirst(false)
            startActivity(Intent(this@WelcomActivity, MainActivity::class.java))
            finish()
            //带点渐变动画
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
    }

}