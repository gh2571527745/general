package com.ld.general_framework.ext

import android.view.View


/**
 *  author : ld
 *  time   : 2020/12/30
 *  desc   :View的拓展函数
 */

/**
 * 设置View隐藏
 */
fun View.gone(){
    visibility = View.GONE
}
/**
 * 设置view显示
 */
fun View.visible() {
    visibility = View.VISIBLE
}