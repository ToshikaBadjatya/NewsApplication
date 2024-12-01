package com.example.newsapplication.data.remote.api

import com.example.newsapplication.Constants
import com.example.newsapplication.Constants.API_KEY
import com.example.newsapplication.data.remote.pojo.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GetNewsApi {
    @GET(Constants.GET_NEWS)
    suspend fun getNewsApi(@Query("page") page:Int, @Query("sources") sources: String,
                           @Query("apiKey") apiKey: String = API_KEY):NewsResponse
}