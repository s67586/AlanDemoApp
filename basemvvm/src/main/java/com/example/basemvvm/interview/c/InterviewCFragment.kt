package com.example.basemvvm.interview.c

import androidx.navigation.fragment.navArgs
import com.example.basemvvm.R
import com.example.basemvvm.base.BaseFragment
import com.example.basemvvm.databinding.FragmentCBinding

/****************************************************
 * Author: alanlai
 * Create Date: 2020/10/28
 * Usage:
 *
 * Revision History
 * Date         Author           Description
 ****************************************************/

class InterviewCFragment : BaseFragment<FragmentCBinding>() {
    val args: InterviewCFragmentArgs by navArgs()
    override fun getLayoutId(): Int {
        return R.layout.fragment_c
    }

    override fun initConfiguration() {
        mViewDataBinding.itemModel = args.itemModel
    }

    override fun initListener() {
    }

    override fun observeLiveData() {
    }
}