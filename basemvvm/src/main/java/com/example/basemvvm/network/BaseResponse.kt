package com.example.basemvvm.network

/****************************************************
 * Copyright (C) Alan Corporation. All rights reserved.
 *
 * Author: AlanLai
 * Create Date: 2020/6/4
 * Usage:
 *
 * Revision History
 * Date         Author           Description
 ****************************************************/

sealed class BaseResponse<out T> {

    data class Success<out T>(val data: T) : BaseResponse<T>()
    data class Error(val exception: Exception) : BaseResponse<Nothing>()
}