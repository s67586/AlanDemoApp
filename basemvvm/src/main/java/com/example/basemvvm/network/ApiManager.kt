package com.example.basemvvm.network

import com.example.basemvvm.model.ResponsePhotosModel

/****************************************************
 * Author: alanlai
 * Create Date: 2020/10/28
 * Usage:
 *
 * Revision History
 * Date         Author           Description
 ****************************************************/

object ApiManager {
    suspend fun getPhotos(): BaseResponse<ResponsePhotosModel> {
        return Client.safeApiCall { Client.apiService.getPhotos() }
    }
}