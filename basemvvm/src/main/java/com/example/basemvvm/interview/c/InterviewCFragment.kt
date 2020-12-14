package com.example.basemvvm.interview.c

import com.example.basemvvm.R
import com.example.basemvvm.base.BaseFragment
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

class InterviewCFragment : BaseFragment<FragmentABinding>() {
    override fun getLayoutId(): Int {
        return R.layout.fragment_c
    }

    override fun initConfiguration() {
    }

    override fun initListener() {
    }

    override fun observeLiveData() {
    }
}