package com.example.basemvvm.network

/****************************************************
 * Copyright (C) Alan Corporation. All rights reserved.
 *
 * Author: AlanLai
 * Create Date: 2020/7/22
 * Usage:
 *
 * Revision History
 * Date         Author           Description
 ****************************************************/

data class BaseResponse<out T>(val status: ApiStatus, val data: T?, val message: NetworkErrorMassage?) {
    companion object {
        fun <T> success(data: T): BaseResponse<T> = BaseResponse(status = ApiStatus.SUCCESS, data = data, message = null)
        fun <T> error(data: T?, message: NetworkErrorMassage?): BaseResponse<T> = BaseResponse(status = ApiStatus.ERROR, data = data, message = message)
    }
}