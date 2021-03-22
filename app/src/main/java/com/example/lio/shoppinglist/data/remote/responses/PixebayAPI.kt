package com.example.lio.shoppinglist.data.remote.responses

import com.example.lio.shoppinglist.BuildConfig
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PixebayAPI {

    @GET("/api/")
    suspend fun searcheForImage(
        @Query("q") searchQuery: String,
        @Query("key") apiKey: String = BuildConfig.API_KEY
    ): Response<ImageResponse>


}