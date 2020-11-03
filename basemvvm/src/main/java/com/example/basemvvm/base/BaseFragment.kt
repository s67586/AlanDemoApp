package com.example.basemvvm.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import java.lang.reflect.ParameterizedType

/****************************************************
 * Author: AlanLai
 * Create Date: 2020/7/27
 * Usage:
 *
 * Revision History
 * Date         Author           Description
 ****************************************************/

abstract class BaseFragment<B : ViewDataBinding, VM : AndroidViewModel> : Fragment() {

    lateinit var mViewDataBinding: B
    lateinit var mViewModel: VM
    val mNavController by lazy { NavHostFragment.findNavController(this) }

    abstract fun getLayoutId(): Int

    abstract fun initConfiguration()

    abstract fun initListener()

    abstract fun observeLiveData()

    open fun getViewModelFactory(): ViewModelProvider.Factory? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root: View = DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(container!!.context), getLayoutId(), container, false).root
        mViewDataBinding = DataBindingUtil.bind(root)!!
        createViewModel(getViewModelFactory())
        mViewDataBinding.lifecycleOwner = this
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initConfiguration()
        initListener()
        observeLiveData()
    }

    open fun createViewModel(newInstanceFactory: ViewModelProvider.Factory?) {
        //獲得類的泛型的類型
        val type =
                javaClass.genericSuperclass as ParameterizedType?
        if (type != null) {
            val actualTypeArguments =
                    type.actualTypeArguments
            val tClass = actualTypeArguments[1] as Class<VM>

            mViewModel = if (newInstanceFactory != null) {
                ViewModelProvider(this, newInstanceFactory).get(tClass)
            } else {
                ViewModelProvider(this).get(tClass)
            }
        }
    }
}