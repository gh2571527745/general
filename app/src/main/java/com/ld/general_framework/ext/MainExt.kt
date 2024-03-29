package com.ld.general_framework.ext

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.alibaba.android.arouter.launcher.ARouter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ld.general_framework.ui.activity.WelcomActivity
import com.ld.lib_base.base.appContext
import com.ld.lib_base.arouter.RouterActivityPath
import com.ld.lib_widget.widget.util.SettingUtil
import com.ld.module_home.fragment.HomeFragment
import com.ld.module_other.fragment.OtherFragment

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
                    return OtherFragment()
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

/**
 * 主页面
 */
fun WelcomActivity.lanchMain() {
    ARouter.getInstance().build(RouterActivityPath.Main.PAGE_MAIN).navigation()
}

fun Activity.lanchLogin() {
    ARouter.getInstance().build(RouterActivityPath.Login.PAGER_LOGIN).navigation()
}


/**
 * ViewPager初始化
 */
fun BottomNavigationView.init(navigationItemSelectedAction: (Int) -> Unit): BottomNavigationView {
    itemIconTintList=null
    setOnNavigationItemSelectedListener {
        navigationItemSelectedAction.invoke(it.itemId)
        true
    }
    return this
}

/**
 * 拦截BottomNavigation长按事件 防止长按时出现Toast
 * @receiver BottomNavigationViewEx
 * @param ids IntArray
 */
fun BottomNavigationView.interceptLongClick(vararg ids: Int) {
    val bottomNavigationMenuView: ViewGroup = (this.getChildAt(0) as ViewGroup)
    for (index in ids.indices) {
        bottomNavigationMenuView.getChildAt(index).findViewById<View>(ids[index])
            .setOnLongClickListener {
                true
            }
    }
}