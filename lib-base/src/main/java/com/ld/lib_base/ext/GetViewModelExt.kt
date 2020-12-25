package com.ld.lib_base.ext

import java.lang.reflect.ParameterizedType

/**
 *  author : ld
 *  time   : 2020/12/25
 *  desc   :
 */

/**
 *
 */
@Suppress("UNCHECKED_CAST")
fun <VM> getVmClazz(obj: Any): VM {
    return (obj.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as VM
}