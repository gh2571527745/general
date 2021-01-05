package com.ld.module_other.fragment

import android.os.Bundle
import com.blankj.utilcode.util.LogUtils
import com.ld.lib_base.base.fragment.BaseFragment
import com.ld.lib_base.base.viewmodel.BaseViewModel
import com.ld.module_other.R
import com.ld.module_other.databinding.FragmentOtherBinding
import kotlinx.android.synthetic.main.fragment_other.*

/**
 *  author : ld
 *  time   : 2021/01/05
 *  desc   :
 */
class OtherFragment : BaseFragment<BaseViewModel, FragmentOtherBinding>() {
    override fun layoutId(): Int = R.layout.fragment_other

    override fun initView(savedInstanceState: Bundle?) {
        LogUtils.e("OtherFragment")

    }
}