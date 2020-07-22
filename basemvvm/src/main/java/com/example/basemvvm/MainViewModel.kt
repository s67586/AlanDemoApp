package com.example.basemvvm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.basemvvm.network.ApiStatus
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

class MainViewModel(application: Application, private val mMainRepository: MainRepository) : AndroidViewModel(application) {

    fun rt() {
        viewModelScope.launch {
            ALog.logError("1213123")
            val errr = mMainRepository.tttt()
            ALog.logError("tt213131tt")
            when (errr.status) {
                ApiStatus.SUCCESS -> {
                    ALog.logError("SUCCESS = " + (errr.data?.get(0)?.mAddr ?: ""))
                }
                ApiStatus.ERROR -> {
                    ALog.logError("ERROR")
                }
            }
        }
    }

    class Factory(
            private val application: Application,
            private var mMainRepository: MainRepository
    ) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainViewModel(application, mMainRepository) as T
        }
    }
}