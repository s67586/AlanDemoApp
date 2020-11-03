package com.example.basemvvm.network

import com.example.basemvvm.BuildConfig
import com.example.basemvvm.model.ResponseMovieNowPlayingModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/3/movie/now_playing")
    suspend fun getNowPlaying(@Query("api_key") mApiKey: String = BuildConfig.TMDB_API_KEY,
                      @Query("language") mLanguage: String = "zh-TW",
                      @Query("page") mPage: Int = 1): Response<ResponseMovieNowPlayingModel>
}
