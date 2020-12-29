package com.ld.general_framework.util

import android.text.TextUtils
import com.google.gson.Gson
import com.ld.general_framework.model.bean.UserInfo
import com.tencent.mmkv.MMKV

/**
 *  author : ld
 *  time   : 2020/12/29
 *  desc   :
 */
object CacheUtil {

    /**
     * 获取保存的账户信息
     */
    fun getUser(): UserInfo? {
        val kv = MMKV.mmkvWithID("app")
        val userStr = kv.decodeString("user")
        return if (TextUtils.isEmpty(userStr)) {
            null
        } else {
            Gson().fromJson(userStr, UserInfo::class.java)
        }
    }

    /**
     * 设置账户信息
     */
    fun setUser(userInfo: UserInfo?) {
        val kv = MMKV.mmkvWithID("app")
        if (userInfo == null) {
            kv.encode("user", "")
            setIsLogin(false)
        } else {
            kv.encode("user", Gson().toJson(userInfo))
            setIsLogin(true)
        }
    }

    /**
     * 是否已经登录
     */
    fun isLogin(): Boolean {
        val kv = MMKV.mmkvWithID("app")
        return kv.decodeBool("login", false)
    }

    /**
     * 设置是否已经登录
     */
    fun setIsLogin(isLogin: Boolean) {
        val kv = MMKV.mmkvWithID("app")
        kv.encode("login", isLogin)
    }
    /**
     * 是否是第一次登陆
     */
    fun isFirst(): Boolean {
        val kv = MMKV.mmkvWithID("app")
        return kv.decodeBool("first", true)
    }
    /**
     * 是否是第一次登陆
     */
    fun setFirst(first:Boolean): Boolean {
        val kv = MMKV.mmkvWithID("app")
        return kv.encode("first", first)
    }
}