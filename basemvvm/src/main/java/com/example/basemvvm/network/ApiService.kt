package com.example.basemvvm.network

import com.example.basemvvm.model.test
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("api/getPostData")
    suspend fun callTest(@Query("hsn") hsn: String,
                         @Query("storeCd") storeCd: String,
                         @Query("town") town: String): Response<test>
}
