package com.ld.lib_base.base.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ld.lib_base.base.viewmodel.BaseViewModel
import com.ld.lib_base.constant.StringConstant

/**
 *  author : ld
 *  time   : 2020/12/21
 *  desc   :
 */
abstract class BaseVmActivity<VM : BaseViewModel> : AppCompatActivity() {
    /**
     * 是否使用DataBinding 供自雷BaseVmDbActivity修改
     */
    private var isUseDB = false

    lateinit var mViewModel: VM

    abstract fun layoutId(): Int

    abstract fun showLoading(message: String = StringConstant.STRING_NET_LOADING)

    abstract fun dismissLoading()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!isUseDB) {
            setContentView(layoutId())
        } else {
            initDataBind()
        }
        init(savedInstanceState)
    }

    private fun init(savedInstanceState: Bundle?) {
//        mViewModel = createViewModel()
//        registerUiChange()
//        initView(savedInstanceState)
//        createObserver()
    }

    open fun initDataBind() {}
}