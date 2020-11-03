package com.example.basemvvm.interview.a

import com.example.basemvvm.R
import com.example.basemvvm.base.BaseFragmentHaveActivityVM
import com.example.basemvvm.databinding.FragmentABinding
import com.example.basemvvm.interview.InterviewViewModel
import com.example.basemvvm.network.ApiManager
import com.example.basemvvm.network.ApiService
import com.example.basemvvm.network.Client
import kotlinx.android.synthetic.main.fragment_a.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/****************************************************
 * Author: alanlai
 * Create Date: 2020/10/28
 * Usage:
 *
 * Revision History
 * Date         Author           Description
 ****************************************************/

class InterviewAFragment : BaseFragmentHaveActivityVM<FragmentABinding, InterviewViewModel, InterviewAViewModel>() {
    override fun getLayoutId(): Int {
        return R.layout.fragment_a
    }

    override fun initConfiguration() {

    }

    override fun initListener() {
        contentButton.setOnClickListener {
            GlobalScope.launch (Dispatchers.IO){
               Client.safeApiCall { Client.apiService.getNowPlaying() }

            }
        }
    }

    override fun observeLiveData() {
    }
}