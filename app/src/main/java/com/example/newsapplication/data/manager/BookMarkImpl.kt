package com.example.newsapplication.data.manager

import com.example.newsapplication.data.local.ArticleDao
import com.example.newsapplication.data.remote.pojo.Article
import com.example.newsapplication.domain.BookmarkRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookMarkImpl@Inject constructor(private val articleDao: ArticleDao):BookmarkRepo {
    override suspend fun bookMarkArticle(article: Article) {
        articleDao.insertArticle(article = article)

    }

    override suspend fun getBookMarkedArticles(): Flow<List<Article>> {
       return articleDao.getArticles()
    }

    override suspend fun deleteArticle(article: Article) {
        articleDao.deleteArticle(article)
    }
}