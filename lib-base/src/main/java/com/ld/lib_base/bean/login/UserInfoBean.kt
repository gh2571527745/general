package com.ld.lib_base.bean.login

import android.annotation.SuppressLint
import android.os.Parcelable
import androidx.versionedparcelable.ParcelField
import kotlinx.android.parcel.Parcelize

/**
 *  author : ld
 *  time   : 2021/01/07
 *  desc   :
 */

@SuppressLint("ParcelCreator")
@Parcelize
data class UserInfoBean(
    var admin: Boolean = false,
    var chapterTops: List<String> = listOf(),
    var collectIds: MutableList<String> = mutableListOf(),
    var email: String = "",
    var icon: String = "",
    var id: String = "",
    var nickname: String = "",
    var password: String = "",
    var token: String = "",
    var type: Int = 0,
    var username: String = ""
) : Parcelable