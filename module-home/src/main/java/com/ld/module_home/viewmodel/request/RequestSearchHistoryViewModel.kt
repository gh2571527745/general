package com.ld.module_home.viewmodel.request

import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import com.blankj.utilcode.util.TimeUtils
import com.ld.lib_base.arouter.homeApi
import com.ld.lib_base.base.viewmodel.BaseViewModel
import com.ld.lib_base.bean.home.ArticleListBean
import com.ld.lib_base.bean.home.SearchHistoryHotBean
import com.ld.lib_base.ext.requestNoCheck
import com.ld.lib_base.network.network.ApiResponse
import com.ld.lib_base.state.ResultState
import com.ld.lib_db.entity.SearchHistoryEntity
import com.ld.lib_db.manager.DbUtil

/**
 *  author : ld
 *  time   : 2021/01/19
 *  desc   :
 */
class RequestSearchHistoryViewModel : BaseViewModel() {

    //页码
    var pageNo = 0

    var getHotSearchResult =
        MutableLiveData<ResultState<ApiResponse<ArrayList<SearchHistoryHotBean>>>>()

    var getSearch = MutableLiveData<ResultState<ApiResponse<ArticleListBean>>>()

    var inputSearch =
        MutableLiveData(DbUtil.instance.getAppDataBase().searchHistoryDao().selectHis())

    fun getHotSearchHistory() {
        requestNoCheck({ homeApi().hotSearch() }, getHotSearchResult, false)
    }

    fun getSearch(isRefresh: Boolean, k: String) {
        if (isRefresh) {
            pageNo = 0
        } else {
            pageNo++
        }
        requestNoCheck({ homeApi().search(pageNo, k) }, getSearch, false)
    }

    fun delteById(id: Long) {
        //数据库删除数据
        DbUtil.instance.getAppDataBase().searchHistoryDao().deleteById(id)
        //viewModel更新
        inputSearch.value = DbUtil.instance.getAppDataBase().searchHistoryDao().selectHis()
    }

    fun saveDb(trim: String) {
        DbUtil.instance.getAppDataBase().searchHistoryDao()
            .insertPerson(SearchHistoryEntity(trim, TimeUtils.getNowDate()))

        inputSearch.value = DbUtil.instance.getAppDataBase().searchHistoryDao().selectHis()
    }
}