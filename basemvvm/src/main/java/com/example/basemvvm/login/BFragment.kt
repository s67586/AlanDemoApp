package com.example.basemvvm.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.basemvvm.R

/****************************************************
 * Copyright (C) 雲端互動 Corporation. All rights reserved.
 *
 * Author: AlanLai
 * Create Date: 2020/7/28
 * Usage:
 *
 * Revision History
 * Date         Author           Description
 ****************************************************/

class BFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_b, container, false)
    }
}