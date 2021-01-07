package com.ld.lib_base.base.activity

import android.content.Context
import android.graphics.PixelFormat
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.kingja.loadsir.core.LoadService
import com.ld.lib_base.base.viewmodel.BaseViewModel
import com.ld.lib_base.ext.getVmClazz
import com.ld.lib_base.network.network.manager.NetState
import com.ld.lib_base.network.network.manager.NetworkStateManager
import com.ld.lib_common.R

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

    /**
     * 提示的view
     */
    protected lateinit var mTipView: View
    protected lateinit var mWindowManager: WindowManager
    protected lateinit var mLayoutParams: WindowManager.LayoutParams

    protected lateinit var loadsir: LoadService<Any>

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
        initView(savedInstanceState)
        createObserver()
        NetworkStateManager.instance.mNetworkStateCallback.observeInActivity(this, {
            onNetworkStateChanged(it)
        })
        initTipView()
    }

    /**
     * 初始化tips
     */
    private fun initTipView() {
        mTipView = layoutInflater.inflate(R.layout.layout_network_tip, null)
        mWindowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        mLayoutParams = WindowManager.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_APPLICATION,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            PixelFormat.TRANSLUCENT
        )
        mLayoutParams.gravity = Gravity.TOP
        mLayoutParams.x = 0
        mLayoutParams.y = 0
        mLayoutParams.windowAnimations = R.style.anim_float_view
    }

    /**
     * 网络变化监听
     */
    open fun onNetworkStateChanged(it: NetState) {}

    /**
     * 创建LiveData数据观察类
     */
    abstract fun createObserver()

    /**
     * 初始化布局
     */
    abstract fun initView(savedInstanceState: Bundle?)

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

    fun userDataBinding(isUserDb: Boolean) {
        this.isUseDb = isUserDb
    }
}