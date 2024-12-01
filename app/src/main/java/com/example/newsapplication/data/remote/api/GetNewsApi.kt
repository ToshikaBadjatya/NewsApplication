package com.example.newsapplication.data.remote.api

import com.example.newsapplication.Constants
import com.example.newsapplication.Constants.API_KEY
import com.example.newsapplication.data.remote.pojo.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GetNewsApi {
    @GET("everything")
    suspend fun getNewsApi(
        @Query("sources") sources: String,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse
}