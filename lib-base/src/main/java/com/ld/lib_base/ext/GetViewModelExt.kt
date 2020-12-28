package com.ld.lib_base.ext

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ld.lib_base.base.BaseApp
import com.ld.lib_base.base.viewmodel.BaseViewModel
import java.lang.reflect.ParameterizedType

/**
 *  author : ld
 *  time   : 2020/12/25
 *  desc   :
 */

/**
 *获取当前类绑定的泛型ViewModel
 */
@Suppress("UNCHECKED_CAST")
fun <VM> getVmClazz(obj: Any): VM {
    return (obj.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as VM
}

/**
 * 在Activity中得到Application上下文的ViewModel
 */
inline fun <reified VM : BaseViewModel> AppCompatActivity.getAppViewModel(): VM {
    (this.application as? BaseApp).let {
        if (it == null) {
            throw NullPointerException("你的Application没有继承框架自带的BaseApp类，暂时无法使用getAppViewModel该方法")
        } else {
            return it.getAppViewModelProvider().get(VM::class.java)
        }
    }
}

/**
 * 在 Frament中得到Applicaiton上下文的ViewModel
 * 提示，在Fragment中调用该方法，请在Fragment onCreate以后调用，或者用by lazy方式懒加载调用，不然会提示requireActivity没有导致错误
 */
inline fun <reified VM : BaseViewModel> Fragment.getAppViewModel(): VM {
    (this.requireActivity().application as? BaseApp).let {
        if (it == null) {
            throw NullPointerException("你的Application没有继承框架自带的BaseApp类，暂时无法使用getAppViewModel该方法")
        } else {
            return it.getAppViewModelProvider().get(VM::class.java)
        }
    }
}