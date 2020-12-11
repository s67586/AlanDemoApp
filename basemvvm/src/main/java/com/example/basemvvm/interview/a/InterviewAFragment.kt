package com.example.basemvvm.interview.a

import com.example.basemvvm.R
import com.example.basemvvm.base.BaseFragment
import com.example.basemvvm.databinding.FragmentABinding
import com.example.basemvvm.interview.InterviewViewModel
import kotlinx.android.synthetic.main.fragment_a.*
import kotlinx.coroutines.*

/****************************************************
 * Author: alanlai
 * Create Date: 2020/10/28
 * Usage:
 *
 * Revision History
 * Date         Author           Description
 ****************************************************/

class InterviewAFragment : BaseFragment<FragmentABinding>() {
    override fun getLayoutId(): Int {
        return R.layout.fragment_a
    }

    override fun initConfiguration() {
    }

    override fun initListener() {
    }

    override fun observeLiveData() {
    }
}