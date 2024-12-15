package com.example.newsapplication.data.manager

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.example.newsapplication.data.remote.api.GetNewsApi
import com.example.newsapplication.data.remote.paging.NewsPagingSource
import com.example.newsapplication.data.remote.paging.SearchNewsPagingSource
import com.example.newsapplication.data.remote.pojo.Article
import com.example.newsapplication.domain.GetNewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNewsImpl @Inject constructor(val newsApi:GetNewsApi):GetNewsRepository {
    val newsPagingConfig=PagingConfig(pageSize = 20)
    val searchPagingConfig=PagingConfig(pageSize = 20)


    override  fun getNews(sources: String): Flow<PagingData<Article>> {
        val pager=Pager(config = newsPagingConfig,null, pagingSourceFactory = {
            NewsPagingSource(newsApi, sources =sources )
        })
        return pager.flow
    }

    override fun searchNews( searchQuery: String,
                             sources: String,): Flow<PagingData<Article>> {


        val pager=Pager(config = searchPagingConfig,null, pagingSourceFactory = {
            SearchNewsPagingSource(newsApi, sources =sources ,keyword=searchQuery)
        })
        return pager.flow
    }


}