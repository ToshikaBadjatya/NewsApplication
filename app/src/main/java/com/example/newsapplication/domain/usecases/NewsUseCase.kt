package com.example.newsapplication.domain.usecases

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.newsapplication.data.remote.paging.NewsPagingSource
import com.example.newsapplication.data.remote.pojo.Article
import com.example.newsapplication.domain.GetNewsRepository
import com.example.newsapplication.domain.LocalUserManager
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

data class NewsUseCase(val getNewsUsecase: GetNewsUsecase)
class GetNewsUsecase @Inject constructor(private val getNewsRepository: GetNewsRepository){
     fun getNews(sources: List<String>): Flow<PagingData<Article>> {
       return getNewsRepository.getNews(sources = sources.joinToString(","))
    }
}