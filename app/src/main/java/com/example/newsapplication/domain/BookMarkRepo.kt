package com.example.newsapplication.domain

import com.example.newsapplication.data.remote.pojo.Article
import kotlinx.coroutines.flow.Flow

interface BookmarkRepo{
    suspend fun bookMarkArticle(article: Article)
    suspend fun getBookMarkedArticles(): Flow<List<Article>>
    suspend fun deleteArticle(article: Article)
}