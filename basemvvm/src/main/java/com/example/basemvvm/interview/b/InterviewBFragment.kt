package com.example.basemvvm.interview.b

import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.basemvvm.R
import com.example.basemvvm.base.BaseFragment
import com.example.basemvvm.databinding.FragmentBBinding
import com.example.basemvvm.interview.InterviewViewModel
import com.example.basemvvm.interview.PhotosAdapter
import kotlinx.android.synthetic.main.fragment_b.*

/****************************************************
 * Author: alanlai
 * Create Date: 2020/10/28
 * Usage:
 *
 * Revision History
 * Date         Author           Description
 ****************************************************/

class InterviewBFragment : BaseFragment<FragmentBBinding>() {

    val activityViewModel by activityViewModels<InterviewViewModel>()

    override fun getLayoutId(): Int {
        return R.layout.fragment_b
    }

    override fun initConfiguration() {
        fragmentB_recyclerView.layoutManager = GridLayoutManager(context, 4)
        fragmentB_recyclerView.adapter = PhotosAdapter(activityViewModel.photosList)
    }

    override fun initListener() {
    }

    override fun observeLiveData() {
    }
}