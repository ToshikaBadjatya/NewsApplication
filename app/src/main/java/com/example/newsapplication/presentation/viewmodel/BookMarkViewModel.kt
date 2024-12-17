package com.example.newsapplication.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.newsapplication.data.remote.pojo.Article
import com.example.newsapplication.domain.usecases.NewsUseCase
import com.example.newsapplication.intent.DetailEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class BookMarkViewModel@Inject constructor(private val newsUseCase: NewsUseCase) : ViewModel()  {
    private val bookMarkState= mutableStateOf<BookMarkState>(BookMarkState(emptyList()))
    val _bookMarkState:State<BookMarkState> = bookMarkState
    fun onEvent(events: DetailEvents) {
        when (events) {
            is DetailEvents.SaveItem -> {
                viewModelScope.launch {
                    newsUseCase.bookMarkUsecase.bookMarkArticle(events.article)
                }
            }

            is DetailEvents.DeleteArticle -> {
                viewModelScope.launch {
                    newsUseCase.bookMarkUsecase.deleteArticle(events.article)
                }
            }

            is DetailEvents.GetItems -> {
                getItems()
            }
        }
    }
        fun getItems(){
            viewModelScope.launch {
                newsUseCase.bookMarkUsecase.getBookMarked().collectLatest {
                    bookMarkState.value=BookMarkState(it)
                }
            }
        }
    }





data class BookMarkState(val articles:List<Article>)
