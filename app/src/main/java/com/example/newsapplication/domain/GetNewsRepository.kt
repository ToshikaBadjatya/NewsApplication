package com.example.newsapplication.domain

import androidx.paging.PagingData
import com.example.newsapplication.data.remote.pojo.Article
import kotlinx.coroutines.flow.Flow

interface GetNewsRepository {
     fun getNews(sources: String): Flow<PagingData<Article>>

    fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>>
}