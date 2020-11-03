package com.example.basemvvm.interview.b

import com.example.basemvvm.R
import com.example.basemvvm.base.BaseFragmentHaveActivityVM
import com.example.basemvvm.databinding.FragmentABinding
import com.example.basemvvm.interview.InterviewViewModel

/****************************************************
 * Author: alanlai
 * Create Date: 2020/10/28
 * Usage:
 *
 * Revision History
 * Date         Author           Description
 ****************************************************/

class InterviewBFragment : BaseFragmentHaveActivityVM<FragmentABinding, InterviewViewModel, InterviewBViewModel>() {
    override fun getLayoutId(): Int {
        return R.layout.fragment_b
    }

    override fun initConfiguration() {
    }

    override fun initListener() {
    }

    override fun observeLiveData() {
    }
}