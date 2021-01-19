package com.ld.lib_base.bean.home

/**
 *  author : ld
 *  time   : 2021/01/19
 *  desc   :
 */
data class ArticleListBean(
    val curPage: Int,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int
)