package com.ld.module_home.fragment

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.kingja.loadsir.core.LoadService
import com.ld.lib_aop.singleclick.annotation.SingleClick
import com.ld.lib_base.base.fragment.BaseFragment
import com.ld.lib_base.constant.Constant
import com.ld.lib_base.ext.*
import com.ld.module_home.R
import com.ld.module_home.activity.SearchHistoryActivity
import com.ld.module_home.adapter.AriticleAdapter
import com.ld.module_home.databinding.FragmentHomeBinding
import com.ld.module_home.viewmodel.request.RequestHomeFragmentViewModel
import com.ld.module_home.viewmodel.state.HomeViewModel
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.android.synthetic.main.include_recyclerview.*

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
        mDataBinding.click = ProClick()

        loadsir = loadServiceInit(swipeRefresh) {
            loadsir.showLoading()
            requestHomeFragmentViewModel.getHomeData(true)
        }

        rv_search_history_hot.run {
            layoutManager = LinearLayoutManager(context)
            adapter = articalAdapter
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
        articalAdapter.run {
            setOnItemClickListener { adapter, view, position ->
                clickItem(position)
            }
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

    private fun clickItem(position: Int) {
        LogUtils.e("点击了条目")
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
                if (it.errorCode == Constant.NET_DATA_GET_ERROR) {//错误码错误
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

    inner class ProClick {
        @SingleClick
        fun searchClick() {
            LogUtils.e("搜索")
            ActivityUtils.startActivity(SearchHistoryActivity::class.java)
        }
    }
}