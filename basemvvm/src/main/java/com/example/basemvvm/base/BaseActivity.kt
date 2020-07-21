package com.example.basemvvm.base

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.basemvvm.R
import com.example.basemvvm.util.ALogger
import com.example.basemvvm.util.GeneralTool
import java.lang.reflect.ParameterizedType

/****************************************************
 * Copyright (C) Alan Corporation. All rights reserved.
 *
 * Author: AlanLai
 * Create Date: 2020/4/24
 * Usage:
 *
 * Revision History
 * Date         Author           Description
 ****************************************************/

abstract class BaseActivity<T : ViewModel> : AppCompatActivity() {

    val mALog: ALogger by lazy { ALogger.instance }
    lateinit var mViewModel: T
    private var mFirstPressedTime: Long = 0

    abstract fun getLayoutId(): Int

    abstract fun getViewModelFactory(): ViewModelProvider.Factory?

    abstract fun initConfiguration()

    abstract fun observeLiveData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        createViewModel(getViewModelFactory())
        initConfiguration()
        observeLiveData()
    }

    open fun createViewModel(newInstanceFactory: ViewModelProvider.Factory?) {
        //獲得類的泛型的類型
        val type =
            javaClass.genericSuperclass as ParameterizedType?
        if (type != null) {
            val actualTypeArguments =
                type.actualTypeArguments
            val tClass = actualTypeArguments[0] as Class<T>

            mViewModel = if (newInstanceFactory != null) {
                ViewModelProvider(this, newInstanceFactory).get(tClass)
            } else {
                ViewModelProvider(this).get(tClass)
            }
        }
    }

    override fun onBackPressed() {
        if (System.currentTimeMillis() - mFirstPressedTime < 2000) {
            exitApp()
        } else {
            GeneralTool().showToast(
                this,
                resources.getString(R.string.first_back_pressed),
                Toast.LENGTH_SHORT
            )
            mFirstPressedTime = System.currentTimeMillis()
        }
    }

    private fun exitApp() {
        val home = Intent(Intent.ACTION_MAIN)
        home.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        home.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        home.addCategory(Intent.CATEGORY_HOME)
        startActivity(home)

        val am =
            baseContext.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
                ?: return
        try {
            val appTasks = am.appTasks
            for (appTask in appTasks) {
                appTask.finishAndRemoveTask()
            }
        } catch (e: SecurityException) {
        }
    }
}
