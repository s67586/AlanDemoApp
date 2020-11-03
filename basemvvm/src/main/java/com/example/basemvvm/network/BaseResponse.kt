package com.example.basemvvm.network

/****************************************************
 * Author: AlanLai
 * Create Date: 2020/7/22
 * Usage:
 *
 * Revision History
 * Date         Author           Description
 ****************************************************/

sealed class BaseResponse<out T> {

    data class Success<out T>(val data: T) : BaseResponse<T>()
    data class Error(val errorMassage: NetworkErrorMassage) : BaseResponse<Nothing>()
}