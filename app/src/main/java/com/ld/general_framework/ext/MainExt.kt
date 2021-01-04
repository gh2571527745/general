package com.ld.general_framework.ext

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.alibaba.android.arouter.launcher.ARouter
import com.ld.general_framework.ui.activity.WelcomActivity
import com.ld.lib_base.constant.RouterActivityPath
import com.ld.module_home.fragment.HomeFragment

/**
 *  author : ld
 *  time   : 2021/01/04
 *  desc   :Main的拓展函数
 */

fun ViewPager2.initMain(fragment: Fragment): ViewPager2 {
    this.isUserInputEnabled = false
    this.offscreenPageLimit = 2
    adapter = object : FragmentStateAdapter(fragment) {
        override fun createFragment(position: Int): Fragment {
            when (position) {
                0 -> {
                    return HomeFragment()
                }
                1 -> {
                    return HomeFragment()
                }
                else -> {
                    return HomeFragment()
                }
            }
        }

        override fun getItemCount(): Int = 2
    }
    return this
}
fun WelcomActivity.lanchMain(){
    ARouter.getInstance().build(RouterActivityPath.Main.PAGE_MAIN).navigation()
}