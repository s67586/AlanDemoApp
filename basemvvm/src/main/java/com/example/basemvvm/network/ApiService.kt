package com.example.basemvvm.network

import com.example.basemvvm.model.ResponsePhotosModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("photos")
    suspend fun getPhotos():Response<ResponsePhotosModel>
}
