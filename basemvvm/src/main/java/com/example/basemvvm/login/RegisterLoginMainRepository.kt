package com.example.basemvvm.login

import com.example.basemvvm.model.test
import com.example.basemvvm.network.BaseResponse
import com.example.basemvvm.network.Client
import com.example.basemvvm.util.ALog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

/****************************************************
 * Author: AlanLai
 * Create Date: 2020/7/21
 * Usage:
 *
 * Revision History
 * Date         Author           Description
 ****************************************************/

class RegisterLoginMainRepository {

    suspend fun tttt(): BaseResponse<test> {
        return withContext(Dispatchers.IO) {
            Client.safeApiCall { Client.apiService.callTest(hsn = "台北市", storeCd = "031123", town = "松山區") }
        }
    }
}