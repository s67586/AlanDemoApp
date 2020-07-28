package com.example.basemvvm

import androidx.lifecycle.ViewModelProvider
import com.example.basemvvm.base.BaseActivity
import com.example.basemvvm.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>() {

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun getViewModelFactory(): ViewModelProvider.Factory? {
        return LoginViewModel.Factory(application, LoginRepository())
    }

    override fun initConfiguration() {
        mViewDataBinding.viewModel = mViewModel
    }

    override fun observeLiveData() {
    }
}
