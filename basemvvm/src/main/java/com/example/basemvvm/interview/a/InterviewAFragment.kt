package com.example.basemvvm.interview.a

import com.example.basemvvm.R
import com.example.basemvvm.base.BaseFragment
import com.example.basemvvm.databinding.FragmentABinding
import kotlinx.android.synthetic.main.fragment_a.*

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
        contentButton.setOnClickListener {
            mNavController.navigate(R.id.action_AFragment_to_BFragment)
        }
    }

    override fun observeLiveData() {
    }
}