package com.ld.module_home.activity

import android.os.Bundle
import android.text.TextUtils
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.vlayout.VirtualLayoutManager
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.TimeUtils
import com.blankj.utilcode.util.ToastUtils
import com.ld.lib_base.base.activity.BaseActivity
import com.ld.lib_base.bean.home.SearchHotBean
import com.ld.lib_base.ext.loadServiceInit
import com.ld.lib_base.ext.parseState
import com.ld.lib_base.ext.showError
import com.ld.lib_base.ext.showLoading
import com.ld.lib_db.dao.SearchHistoryDao
import com.ld.lib_db.entity.SearchHistoryEntity
import com.ld.lib_db.manager.DbUtil
import com.ld.module_home.R
import com.ld.module_home.adapter.SearchHistoryHotAdapter
import com.ld.module_home.adapter.SearchHistorySearchAdapter
import com.ld.module_home.databinding.ActivitySearchHistoryBinding
import com.ld.module_home.viewmodel.request.RequestSearchHistoryViewModel
import kotlinx.android.synthetic.main.activity_search_history.*

/**
 *  author : ld
 *  time   : 2021/01/19
 *  desc   :
 */
class SearchHistoryActivity :
    BaseActivity<RequestSearchHistoryViewModel, ActivitySearchHistoryBinding>() {

    override fun layoutId() = R.layout.activity_search_history

    private val mSearchHistoryHotAdapter: SearchHistoryHotAdapter by lazy {
        SearchHistoryHotAdapter(arrayListOf())
    }
    private val mSearchHistorySearchAdapter: SearchHistorySearchAdapter by lazy {
        SearchHistorySearchAdapter(arrayListOf())
    }

    private val requestSearchHistoryViewModel: RequestSearchHistoryViewModel by viewModels()

    override fun initView(savedInstanceState: Bundle?) {
        LogUtils.e("SearchHistoryActivity")

        loadsir = loadServiceInit(rv_search_history_hot) {
            requestSearchHistoryViewModel.getHotSearchHistory()
        }

        rv_search_history_hot.run {
            layoutManager = LinearLayoutManager(context)
            adapter = mSearchHistoryHotAdapter
        }
        mSearchHistoryHotAdapter.run {
            setOnSearchHotCallBack(object : SearchHistoryHotAdapter.OnSearchHotCallBack {
                override fun onHot(title: String) {
                    LogUtils.e(title)
                }
            })
        }

        rv_search_history_history.run {
            layoutManager = LinearLayoutManager(context)
            adapter = mSearchHistorySearchAdapter
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
        mSearchHistorySearchAdapter.run {
            addChildClickViewIds(R.id.ivCha)
            setOnItemChildClickListener { adapter, view, position ->
                when (view.id) {
                    R.id.ivCha -> {
                        LogUtils.e("删除" + mSearchHistorySearchAdapter.data.get(position).id)
                        requestSearchHistoryViewModel.delteById(mSearchHistorySearchAdapter.data.get(position).id)
                    }
                }
            }
        }

        requestSearchHistoryViewModel.getHotSearchHistory()

        et_search_history.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    val trim = et_search_history.text.toString().trim()
                    if (TextUtils.isEmpty(trim)) {
                        ToastUtils.showShort("请输入内容")
                    } else {
                        saveDB(trim)
                    }
                    return false
                }
                return false
            }
        })
    }

    private fun saveDB(trim: String) {
        requestSearchHistoryViewModel.saveDb(trim)
    }

    override fun createObserver() {
        requestSearchHistoryViewModel.getHotSearchResult.observe(this, Observer { result ->
            parseState(result, {
                loadsir.showLoading()
            }, {
                if (it.errorCode == -1) {//错误码错误
                    ToastUtils.showShort(it.errorMsg)
                    return@parseState
                }
                loadsir.showSuccess()
                LogUtils.e(it.data)
                var lists = ArrayList<SearchHotBean>()
                var bean = SearchHotBean("热门搜索", it.data)
                lists.add(bean)
                mSearchHistoryHotAdapter.setList(lists)
            }, {
                loadsir.showError()
            })
        })
        requestSearchHistoryViewModel.inputSearch.observe(this, Observer {
            LogUtils.e("刷新")
            mSearchHistorySearchAdapter.setList(it)
        })
    }
}