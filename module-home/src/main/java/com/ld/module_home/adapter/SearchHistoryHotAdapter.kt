package com.ld.module_home.adapter

import android.view.LayoutInflater
import androidx.appcompat.widget.AppCompatTextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.google.android.flexbox.FlexboxLayout
import com.ld.lib_base.bean.home.SearchHistoryHotBean
import com.ld.lib_base.bean.home.SearchHotBean
import com.ld.module_home.R
import com.ld.module_home.databinding.ActivityHomeBinding.inflate
import java.util.zip.Inflater

/**
 *  author : ld
 *  time   : 2021/01/19
 *  desc   :
 */
class SearchHistoryHotAdapter(listsBean: ArrayList<SearchHotBean>) :
    BaseQuickAdapter<SearchHotBean, BaseViewHolder>(R.layout.item_search_hot, listsBean) {
    override fun convert(holder: BaseViewHolder, item: SearchHotBean) {
        holder.setText(R.id.tv_item_search_hot_title, item.title)

        var flexLayout = holder.getView<FlexboxLayout>(R.id.flexLayout)
        flexLayout.removeAllViews()
        for (i in item.data) {
            val labelTv: AppCompatTextView = createOrGetCacheTv(flexLayout)
            labelTv.setText(i.name)
            labelTv.setOnClickListener {
                onSearchHotCallBack.onHot(i.name)
            }
            flexLayout.addView(labelTv)
        }


    }

    private fun createOrGetCacheTv(flexLayout: FlexboxLayout): AppCompatTextView {
        return LayoutInflater.from(flexLayout.context).inflate(
            R.layout.flextlayout_item_label,
            flexLayout,
            false
        ) as AppCompatTextView

    }

    interface OnSearchHotCallBack {
        fun onHot(title: String)
    }

    private lateinit var onSearchHotCallBack: OnSearchHotCallBack
    public fun setOnSearchHotCallBack(onSearchHotCallBack: OnSearchHotCallBack) {
        this.onSearchHotCallBack = onSearchHotCallBack
    }
}