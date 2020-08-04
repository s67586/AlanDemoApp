package com.example.basemvvm.login

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import com.example.basemvvm.R
import com.example.basemvvm.base.BaseActivity
import com.example.basemvvm.databinding.ActivityLoginBinding


class LoginMainActivity : BaseActivity<ActivityLoginBinding, LoginMainViewModel>() {

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun getViewModelFactory(): ViewModelProvider.Factory? {
        return LoginMainViewModel.Factory(application, LoginMainRepository())
    }

    override fun initConfiguration() {
        mViewDataBinding.viewModel = mViewModel

    }

    override fun observeLiveData() {
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        mViewModel.mFacebookCallbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }
}
