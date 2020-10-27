package com.example.basemvvm.login

import android.app.Application
import androidx.lifecycle.*
import com.example.basemvvm.model.test
import com.example.basemvvm.network.ApiStatus
import com.example.basemvvm.network.BaseResponse
import com.example.basemvvm.util.ALog
import com.facebook.CallbackManager
import kotlinx.coroutines.launch


/****************************************************
 * Author: AlanLai
 * Create Date: 2020/7/21
 * Usage:
 *
 * Revision History
 * Date         Author           Description
 ****************************************************/

class RegisterLoginMainViewModel(application: Application, private val mRegisterLoginMainRepository: RegisterLoginMainRepository) :
        AndroidViewModel(application) {

    var liveData: MutableLiveData<BaseResponse<test>> = MutableLiveData()
    var mFacebookCallbackManager: CallbackManager = CallbackManager.Factory.create()


    fun callApi() {
        viewModelScope.launch {
            ALog.logError("1213123")
            val errr = mRegisterLoginMainRepository.tttt()
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
            private var mRegisterLoginMainRepository: RegisterLoginMainRepository
    ) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return RegisterLoginMainViewModel(application, mRegisterLoginMainRepository) as T
        }
    }
}