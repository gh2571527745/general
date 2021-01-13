package com.ld.module_home.fragment

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.kingja.loadsir.core.LoadService
import com.ld.lib_base.base.fragment.BaseFragment
import com.ld.lib_base.base.viewmodel.BaseViewModel
import com.ld.lib_base.ext.*
import com.ld.lib_base.util.CacheUtil
import com.ld.module_home.R
import com.ld.module_home.adapter.AriticleAdapter
import com.ld.module_home.databinding.FragmentHomeBinding
import com.ld.module_home.viewmodel.request.RequestHomeFragmentViewModel
import com.ld.module_home.viewmodel.state.HomeViewModel
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.android.synthetic.main.include_recyclerview.*
import kotlinx.android.synthetic.main.include_toolbar.*

/**
 *  author : ld
 *  time   : 2021/01/04
 *  desc   :
 */
class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {
    private lateinit var loadsir: LoadService<Any>

    private val requestHomeFragmentViewModel: RequestHomeFragmentViewModel by viewModels()

    private val articalAdapter: AriticleAdapter by lazy { AriticleAdapter(arrayListOf(), true) }
    override fun layoutId(): Int = R.layout.fragment_home

    private var isRefresh: Boolean = false

    override fun initView(savedInstanceState: Bundle?) {
        mDataBinding.home = mViewModel

        loadsir = loadServiceInit(swipeRefresh) {
            loadsir.showLoading()
            requestHomeFragmentViewModel.getHomeData(true)
        }

        recyclerView.run {
            layoutManager = LinearLayoutManager(context)
            adapter = articalAdapter
        }
        swipeRefresh.run {
            setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
                override fun onLoadMore(refreshLayout: RefreshLayout) {
                    LogUtils.e("加载更多")
                    isRefresh = false
                    requestHomeFragmentViewModel.getHomeData(isRefresh)
                }

                override fun onRefresh(refreshLayout: RefreshLayout) {
                    LogUtils.e("下拉刷新")
                    isRefresh = true
                    requestHomeFragmentViewModel.getHomeData(isRefresh)
                }

            })
        }
    }

    override fun lazyLoadData() {
        requestHomeFragmentViewModel.getHomeData(true)
    }

    override fun createObserver() {
        appViewModel.run {
        }
        requestHomeFragmentViewModel.homeDataState.observe(this, Observer { resultState ->
            parseState(resultState, {
                loadsir.showLoading()
            }, {
                if (it.errorCode == -1) {//错误码错误
                    ToastUtils.showShort(it.errorMsg)
                    return@parseState
                }

                loadsir.showSuccess()
                if (isRefresh) {
                    swipeRefresh.finishRefresh()
                    articalAdapter.setList(it.data.datas)
                } else {
                    swipeRefresh.finishLoadMore()
                    articalAdapter.addData(it.data.datas)
                }
            }, {//Error错误
                loadsir.showError()
            })
        }
        )
    }
}