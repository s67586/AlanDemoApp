package com.example.basemvvm.network

import com.example.basemvvm.model.ResponseMovieNowPlayingModel

/****************************************************
 * Author: alanlai
 * Create Date: 2020/10/28
 * Usage:
 *
 * Revision History
 * Date         Author           Description
 ****************************************************/

object ApiManager {
    suspend fun getNowPlaying(): BaseResponse<ResponseMovieNowPlayingModel> {
        return Client.safeApiCall { Client.apiService.getNowPlaying() }
    }
}