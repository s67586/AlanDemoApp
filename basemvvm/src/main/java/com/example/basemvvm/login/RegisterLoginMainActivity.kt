package com.example.basemvvm.login

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import com.example.basemvvm.R
import com.example.basemvvm.base.BaseActivity
import com.example.basemvvm.databinding.ActivityLoginBinding


class RegisterLoginMainActivity : BaseActivity<ActivityLoginBinding, RegisterLoginMainViewModel>() {

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun getViewModelFactory(): ViewModelProvider.Factory? {
        return RegisterLoginMainViewModel.Factory(application, RegisterLoginMainRepository())
    }

    override fun initConfiguration() {
        mViewDataBinding.viewModel = mViewModel

    }

    override fun observeLiveData() {
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mViewModel.mFacebookCallbackManager.onActivityResult(requestCode, resultCode, data)
    }
}
