package com.example.basemvvm.base

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.reflect.ParameterizedType

/****************************************************
 * Author: AlanLai
 * Create Date: 2020/4/24
 * Usage:
 *
 * Revision History
 * Date         Author           Description
 ****************************************************/

abstract class BaseActivity<B : ViewDataBinding> : AppCompatActivity() {

    lateinit var mViewDataBinding: B

    abstract fun getLayoutId(): Int

    abstract fun initConfiguration()

    abstract fun initListener()

    abstract fun observeLiveData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        mViewDataBinding.lifecycleOwner = this
        initConfiguration()
        initListener()
        observeLiveData()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) hideSystemUI()
    }

    private fun hideSystemUI() {
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_IMMERSIVE
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
    }
}
