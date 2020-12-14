package com.example.basemvvm.interview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.basemvvm.model.ResponsePhotosModelItem
import com.example.basemvvm.network.ApiManager
import com.example.basemvvm.network.BaseResponse
import kotlinx.coroutines.launch

/****************************************************
 * Author: alanlai
 * Create Date: 2020/10/27
 * Usage:
 *
 * Revision History
 * Date         Author           Description
 ****************************************************/

class InterviewViewModel : ViewModel() {
   lateinit var photosList :ArrayList<ResponsePhotosModelItem>
    fun getPhotos(){
        viewModelScope.launch {
            ApiManager.getPhotos().apply {
                when (this) {
                    is BaseResponse.Success -> {
                        photosList = this.data
                    }
                    is BaseResponse.Error -> {
                    }
                }
            }
        }
    }
}
