package com.example.newsapplication.data.manager

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.newsapplication.data.remote.api.GetNewsApi
import com.example.newsapplication.data.remote.paging.NewsPagingSource
import com.example.newsapplication.data.remote.pojo.Article
import com.example.newsapplication.domain.GetNewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNewsImpl @Inject constructor(val newsApi:GetNewsApi):GetNewsRepository {
    override suspend fun getNews(sources: String): Flow<PagingData<Article>> {
        val pagingConfig=PagingConfig(pageSize = 20)
        val pagingSource=NewsPagingSource(newsApi, sources =sources )
        val pager= Pager(config = pagingConfig,null, pagingSourceFactory = {pagingSource})
        return pager.flow
    }

    override fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        TODO("Not yet implemented")
    }

}