package com.example.basemvvm.model

import com.example.basemvvm.BuildConfig
import com.google.gson.annotations.SerializedName

/****************************************************
 * Author: alanlai
 * Create Date: 2020/10/29
 * Usage:
 *
 * Revision History
 * Date         Author           Description
 ****************************************************/

data class RequestMovieNowPlayingModel(
        @SerializedName("api_key") override val mApiKey: String = BuildConfig.TMDB_API_KEY,
        @SerializedName("language") val mLanguage: String = "zh-TW",
        @SerializedName("page") var mPage: Int = 1)
    : BaseRequestModel