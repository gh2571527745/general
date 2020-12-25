package com.ld.lib_base.base.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.ld.lib_base.base.viewmodel.BaseViewModel
import com.ld.lib_base.ext.getVmClazz
import com.ld.lib_base.network.StringConstant
import com.ld.lib_base.network.network.manager.NetState
import com.ld.lib_base.network.network.manager.NetworkStateManager

/**
 *  author : ld
 *  time   : 2020/12/21
 *  desc   :
 */
abstract class BaseVmActivity<VM : BaseViewModel> : AppCompatActivity() {
    /**
     * 是否使用DataBinding 供自雷BaseVmDbActivity修改
     */
    private var isUseDb = false

    lateinit var mViewModel: VM

    abstract fun layoutId(): Int

    abstract fun showLoading(message: String = StringConstant.STRING_NET_LOADING)

    abstract fun dismissLoading()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!isUseDb) {
            setContentView(layoutId())
        } else {
            initDataBind()
        }
        init(savedInstanceState)
    }

    private fun init(savedInstanceState: Bundle?) {
        mViewModel = createViewModel()
        registerUiChange()
        initView(savedInstanceState)
        createObserver()
        NetworkStateManager.instance.mNetworkStateCallback.observeInActivity(this, {
            onNetworkStateChanged(it)
        })
    }

    /**
     * 网络变化监听
     */
    abstract fun onNetworkStateChanged(it: NetState?)

    /**
     * 创建LiveData数据观察类
     */
    abstract fun createObserver()

    /**
     * 初始化布局
     */
    abstract fun initView(savedInstanceState: Bundle?)

    private fun registerUiChange() {
        mViewModel.loading.showDialog.observeInActivity(this) {
            showLoading(it)
        }
        mViewModel.loading.diamissDialog.observeInActivity(this) {
            dismissLoading()
        }

    }

    /**
     * 创建ViewModel
     */
    private fun createViewModel(): VM {
        return ViewModelProvider(this).get(getVmClazz(this))
    }

    /**
     * 供子类BaseVmDbActivity 初始化Databinding操作
     */
    open fun initDataBind() {}

    protected fun addLoadingObserve(vararg viewModels:BaseViewModel){
        viewModels.forEach { it->
            //显示弹窗
            it.loading.showDialog.observeInActivity(this) {
                showLoading(it)
            }
            //关闭弹窗
            it.loading.diamissDialog.observeInActivity(this) {
                dismissLoading()
            }
        }
    }

    fun userDataBinding(isUserDb:Boolean){
        this.isUseDb = isUserDb
    }
}