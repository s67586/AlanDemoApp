package com.example.basemvvm

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.basemvvm.base.BaseActivity
import com.example.basemvvm.model.test
import com.example.basemvvm.network.BaseResponse
import com.example.basemvvm.network.Client
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import retrofit2.Response

class MainActivity : BaseActivity<MainViewModel>() {

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun getViewModelFactory(): ViewModelProvider.Factory? {
        return MainViewModel.Factory(application, MainRepository())
    }

    override fun initConfiguration() {
    }

    override fun observeLiveData() {

        lifecycleScope.launchWhenStarted {

            val testList: Deferred<BaseResponse<test>> = async {
                Client.safeApiCall { Client.apiService.callTest(hsn = "台北市", storeCd = "031123", town = "松山區") }
            }

            when (val ete = testList.await()) {
                is BaseResponse.Success<test> -> {

                }

                is BaseResponse.Error -> {

                }
            }
        }
    }
}
