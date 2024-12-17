package com.example.newsapplication.domain.usecases

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.room.Dao
import com.example.newsapplication.data.remote.paging.NewsPagingSource
import com.example.newsapplication.data.remote.pojo.Article
import com.example.newsapplication.domain.BookmarkRepo
import com.example.newsapplication.domain.GetNewsRepository
import com.example.newsapplication.domain.LocalUserManager
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

data class NewsUseCase(val getNewsUsecase: GetNewsUsecase,val searchNewsUsecase: SearchNewsUsecase,
    val bookMarkUsecase: BookMarkUseCase)
class GetNewsUsecase @Inject constructor(private val getNewsRepository: GetNewsRepository){
     fun getNews(sources: List<String>): Flow<PagingData<Article>> {
       return getNewsRepository.getNews(sources = sources.joinToString(","))
    }
}

class SearchNewsUsecase @Inject constructor(private val getNewsRepository: GetNewsRepository){
    fun searchNews(sources: List<String>,search:String): Flow<PagingData<Article>> {
        return getNewsRepository.searchNews(sources = sources.joinToString(","), searchQuery = search)
    }
}
class BookMarkUseCase @Inject constructor(private val bookmarkRepo: BookmarkRepo){
     suspend fun bookMarkArticle(article: Article){
         bookmarkRepo.bookMarkArticle(article)
     }
    suspend fun getBookMarked():Flow<List<Article>>{
        return bookmarkRepo.getBookMarkedArticles()
    }

    suspend fun deleteArticle(article: Article) {
        bookmarkRepo.deleteArticle(article)
    }
}