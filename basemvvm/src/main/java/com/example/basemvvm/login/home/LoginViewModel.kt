package com.example.basemvvm.login.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseUser


/****************************************************
 * Author: AlanLai
 * Create Date: 2020/7/21
 * Usage:
 *
 * Revision History
 * Date         Author           Description
 ****************************************************/

class LoginViewModel(application: Application, private val mLoginRepository: LoginRepository) :
        AndroidViewModel(application) {

    var mFireBaseUserLiveData: MutableLiveData<FirebaseUser> = MutableLiveData()


    class Factory(
            private val application: Application,
            private var mLoginRepository: LoginRepository
    ) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return LoginViewModel(application, mLoginRepository) as T
        }
    }
}