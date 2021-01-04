package com.ld.lib_base.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 *  author : ld
 *  time   : 2020/12/29
 *  desc   :
 */
@Parcelize
data class UserInfo(
    var token: String = "",
    var id: String = "",
    var userName: String = "",
    var password: String = ""
) : Parcelable