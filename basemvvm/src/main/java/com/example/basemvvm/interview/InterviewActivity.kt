package com.example.basemvvm.interview

import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.basemvvm.R
import com.example.basemvvm.base.BaseActivity
import com.example.basemvvm.databinding.ActivityInterviewBinding

/****************************************************
 * Author: alanlai
 * Create Date: 2020/10/27
 * Usage:
 *
 * Revision History
 * Date         Author           Description
 ****************************************************/

class InterviewActivity : BaseActivity<ActivityInterviewBinding>() {

    val yrr : ViewModel by viewModels<InterviewViewModel>()
    override fun getLayoutId(): Int {
       return R.layout.activity_interview
    }

    override fun initConfiguration() {
    }

    override fun initListener() {
        
    }

    override fun observeLiveData() {
    }
}