package com.example.basemvvm

import android.app.Application
import androidx.lifecycle.*
import com.example.basemvvm.model.test
import com.example.basemvvm.network.ApiStatus
import com.example.basemvvm.network.BaseResponse
import com.example.basemvvm.util.ALog
import kotlinx.coroutines.launch


/****************************************************
 * Copyright (C) Alan Corporation. All rights reserved.
 *
 * Author: AlanLai
 * Create Date: 2020/7/21
 * Usage:
 *
 * Revision History
 * Date         Author           Description
 ****************************************************/

class LoginViewModel(application: Application, private val mLoginRepository: LoginRepository) :
        AndroidViewModel(application) {
    var liveData: MutableLiveData<BaseResponse<test>> = MutableLiveData()

    fun callApi() {
        viewModelScope.launch {
            ALog.logError("1213123")
            val errr = mLoginRepository.tttt()
            ALog.logError("tt213131tt")
            when (errr.status) {
                ApiStatus.SUCCESS -> {
                    ALog.logError("SUCCESS = " + (errr.data?.get(0)?.mAddr ?: ""))
                    liveData.postValue(errr)
                }
                ApiStatus.ERROR -> {
                    ALog.logError("ERROR")
                }
            }
        }
    }

    class Factory(
            private val application: Application,
            private var mLoginRepository: LoginRepository
    ) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return LoginViewModel(application, mLoginRepository) as T
        }
    }
}