package com.ld.lib_base.bean.home

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 *  author : ld
 *  time   : 2021/01/19
 *  desc   :
 */
@Parcelize
data class SearchHistoryHotBean(
    var id: Int,
    var link: String,
    var name: String,
    var order: Int,
    var visible: Int
) : Parcelable