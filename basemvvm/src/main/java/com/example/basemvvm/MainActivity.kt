package com.example.basemvvm

import androidx.lifecycle.ViewModelProvider
import com.example.basemvvm.base.BaseActivity

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
        mViewModel.rt()
    }
}
