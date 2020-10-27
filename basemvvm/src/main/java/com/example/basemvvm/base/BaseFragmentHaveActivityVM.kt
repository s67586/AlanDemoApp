package com.example.basemvvm.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.reflect.ParameterizedType

/****************************************************
 * Author: AlanLai
 * Create Date: 2020/7/27
 * Usage:
 *
 * Revision History
 * Date         Author           Description
 ****************************************************/

abstract class BaseFragmentHaveActivityVM<B : ViewDataBinding, AVM : ViewModel, VM : ViewModel> : BaseFragment<B, VM>() {

    lateinit var mActivityViewModel: AVM

    abstract fun getActivityViewModelFactory(): ViewModelProvider.Factory?

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root: View = DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(container!!.context), getLayoutId(), container, false).root
        mViewDataBinding = DataBindingUtil.bind(root)!!
        createActivityViewModel(getActivityViewModelFactory())
        createViewModel(getViewModelFactory())
        mViewDataBinding.lifecycleOwner = this
        return root
    }

    open fun createActivityViewModel(newInstanceFactory: ViewModelProvider.Factory?) {
        //獲得類的泛型的類型
        val type =
                javaClass.genericSuperclass as ParameterizedType?
        if (type != null) {
            val actualTypeArguments =
                    type.actualTypeArguments
            val tClass = actualTypeArguments[1] as Class<AVM>

            mActivityViewModel = if (newInstanceFactory != null) {
                ViewModelProvider(requireActivity(), newInstanceFactory).get(tClass)
            } else {
                ViewModelProvider(requireActivity()).get(tClass)
            }
        }
    }
}