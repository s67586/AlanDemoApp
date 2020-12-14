package com.example.basemvvm.interview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.basemvvm.databinding.TestViewHolderBinding
import com.example.basemvvm.model.ResponsePhotosModel
import com.example.basemvvm.model.ResponsePhotosModelItem


/****************************************************
 * Author: alanlai
 * Create Date: 12/11/20
 * Usage:
 *
 * Revision History
 * Date         Author           Description
 ****************************************************/

class PhotosAdapter(private val mDataList: ArrayList<ResponsePhotosModelItem>, private val mTestViewHolderCallBack: TestViewHolderCallBack)
    : RecyclerView.Adapter<PhotosAdapter.TestViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestViewHolder {
        return TestViewHolder(TestViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: TestViewHolder, position: Int) {
        holder.bind(mDataList[position])
        holder.itemView.setOnClickListener {
            mTestViewHolderCallBack.onItemClick(mDataList[position])
        }
    }

    override fun getItemCount(): Int {
        return mDataList.size
    }

    class TestViewHolder(private val mBinding: TestViewHolderBinding) : RecyclerView.ViewHolder(mBinding.root) {
        fun bind(data: ResponsePhotosModelItem) {
            mBinding.model = data
            mBinding.executePendingBindings()
        }
    }
}

interface TestViewHolderCallBack {
    fun onItemClick(responsePhotosModelItem: ResponsePhotosModelItem)
}



