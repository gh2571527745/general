package com.ld.lib_base.base.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import com.ld.lib_base.base.viewmodel.BaseViewModel
import com.ld.lib_base.ext.getVmClazz
import com.ld.lib_base.network.network.manager.NetState
import com.ld.lib_base.network.network.manager.NetworkStateManager

/**
 *  author : ld
 *  time   : 2020/12/28
 *  desc   :
 */
abstract class BaseVmFragment<VM : BaseViewModel> : Fragment() {
    private var isFirst: Boolean = true
    lateinit var mViewModel: VM
    lateinit var mActivity: AppCompatActivity
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutId(), container, false)
    }

    abstract fun layoutId(): Int

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = context as AppCompatActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isFirst = true
        mViewModel = creteViewModel()
        initView(savedInstanceState)
        createObserver()
        registorUiChange()
        initData()
    }

    override fun onResume() {
        super.onResume()
        onVisible()
    }

    private fun onVisible() {
        if (lifecycle.currentState == Lifecycle.State.STARTED && isFirst) {
            //等待view加载后触发懒加载
            view?.post {
                lazyLoadData()
                //在Fragment中，只有懒加载过了才能开启网络变化监听
                NetworkStateManager.instance.mNetworkStateCallback.observeInFragment(this, {
                    //不是首次订阅时调用方法，防止数据第一次监听错误
                    if (!isFirst) {
                        onNetwordStateChanged(it)
                    }
                })
                isFirst = false
            }
        }

    }

    /**
     * 网络变化监听 子类重写
     */
    abstract fun onNetwordStateChanged(it: NetState?)

    /**
     * 懒加载
     */
    abstract fun lazyLoadData()

    /**
     * Fragment执行onCreate后触发的方法
     */
    abstract fun initData()

    private fun registorUiChange() {
        mViewModel.loading.showDialog.observeInFragment(this, {
            showLoading()
        })
        mViewModel.loading.diamissDialog.observeInFragment(this, {
            dismissLoading()
        })
    }

    abstract fun dismissLoading()

    abstract fun showLoading(message: String = "请求网络中...")

    /**
     * 创建观察者
     */
    abstract fun createObserver()

    /**
     * 初始化View
     */
    abstract fun initView(savedInstanceState: Bundle?)

    /**
     * 创建viewModel
     */
    private fun creteViewModel(): VM {
        return ViewModelProvider(this).get(getVmClazz(this))
    }
}