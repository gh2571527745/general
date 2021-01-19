package com.ld.module_home.adapter

import android.view.LayoutInflater
import androidx.appcompat.widget.AppCompatTextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.google.android.flexbox.FlexboxLayout
import com.ld.lib_base.bean.home.SearchHistoryHotBean
import com.ld.lib_base.bean.home.SearchHotBean
import com.ld.lib_db.entity.SearchHistoryEntity
import com.ld.module_home.R
import com.ld.module_home.databinding.ActivityHomeBinding.inflate
import java.util.zip.Inflater

/**
 *  author : ld
 *  time   : 2021/01/19
 *  desc   :
 */
class SearchHistorySearchAdapter(listsBean: ArrayList<SearchHistoryEntity>) :
    BaseQuickAdapter<SearchHistoryEntity, BaseViewHolder>(R.layout.item_search_his, listsBean) {
    override fun convert(holder: BaseViewHolder, item: SearchHistoryEntity) {
        holder.setText(R.id.tvTitle, item.name)
    }
}