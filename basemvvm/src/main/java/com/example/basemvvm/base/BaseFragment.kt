package com.example.basemvvm.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.reflect.ParameterizedType

/****************************************************
 * Copyright (C) 雲端互動 Corporation. All rights reserved.
 *
 * Author: AlanLai
 * Create Date: 2020/7/27
 * Usage:
 *
 * Revision History
 * Date         Author           Description
 ****************************************************/

abstract class BaseFragment<B : ViewDataBinding, AT : ViewModel, T : ViewModel> : Fragment() {

    lateinit var mViewDataBinding: B
    lateinit var mActivityViewModel: AT
    lateinit var mViewModel: T

    abstract fun getLayoutId(): Int

    abstract fun getActivityViewModelFactory(): ViewModelProvider.Factory?

    abstract fun getViewModelFactory(): ViewModelProvider.Factory?

    abstract fun initConfiguration()

    abstract fun observeLiveData()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root: View = DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(container!!.context), getLayoutId(), container, false).root
        mViewDataBinding = DataBindingUtil.bind(root)!!
        createActivityViewModel(getActivityViewModelFactory())
        createViewModel(getViewModelFactory())
        mViewDataBinding.lifecycleOwner = this
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initConfiguration()
        observeLiveData()
    }

    open fun createActivityViewModel(newInstanceFactory: ViewModelProvider.Factory?) {
        //獲得類的泛型的類型
        val type =
                javaClass.genericSuperclass as ParameterizedType?
        if (type != null) {
            val actualTypeArguments =
                    type.actualTypeArguments
            val tClass = actualTypeArguments[1] as Class<AT>

            mActivityViewModel = if (newInstanceFactory != null) {
                ViewModelProvider(requireActivity(), newInstanceFactory).get(tClass)
            } else {
                ViewModelProvider(requireActivity()).get(tClass)
            }
        }
    }

    open fun createViewModel(newInstanceFactory: ViewModelProvider.Factory?) {
        //獲得類的泛型的類型
        val type =
                javaClass.genericSuperclass as ParameterizedType?
        if (type != null) {
            val actualTypeArguments =
                    type.actualTypeArguments
            val tClass = actualTypeArguments[2] as Class<T>

            mViewModel = if (newInstanceFactory != null) {
                ViewModelProvider(this, newInstanceFactory).get(tClass)
            } else {
                ViewModelProvider(this).get(tClass)
            }
        }
    }
}