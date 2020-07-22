package com.example.basemvvm

import com.example.basemvvm.model.test
import com.example.basemvvm.network.BaseResponse
import com.example.basemvvm.network.Client
import com.example.basemvvm.util.ALog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

/****************************************************
 * Copyright (C) Alan Corporation. All rights reserved.
 *
 * Author: AlanLai
 * Create Date: 2020/7/21
 * Usage:
 *
 * Revision History
 * Date         Author           Description
 ****************************************************/

class MainRepository {

    suspend fun tttt(): BaseResponse<test> {
        return withContext(Dispatchers.IO) {
            delay(2000)
            ALog.logError("tttt")
            Client.safeApiCall { Client.apiService.callTest(hsn = "台北市", storeCd = "031123", town = "松山區") }
        }
    }
}