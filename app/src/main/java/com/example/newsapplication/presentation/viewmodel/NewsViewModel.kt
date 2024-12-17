package com.example.newsapplication.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsapplication.data.remote.pojo.Article
import com.example.newsapplication.domain.usecases.NewsUseCase
import com.example.newsapplication.intent.NewsEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

data class SearchState( val pagingData: Flow<PagingData<Article>> ?=null)

@HiltViewModel
class NewsViewModel @Inject constructor(private val newsUseCase: NewsUseCase) : ViewModel() {
    private val searchText= mutableStateOf<String>("")
    val _searchText = searchText.value
    private val searchState = mutableStateOf<SearchState>(SearchState())
    val _searchState : State<SearchState> = searchState

    fun onEvent(event: NewsEvents) {
        when (event) {
            is NewsEvents.SearchEvent -> {
                searchText.value=event.searchText
                val searchFlow = newsUseCase.searchNewsUsecase.searchNews(
                    sources = listOf("bbc-news", "abc-news", "al-jazeera-english"), searchText.value
                ).cachedIn(viewModelScope)
                searchState.value.copy( pagingData = searchFlow)
            }

            is NewsEvents.GetNews -> {
                val news = newsUseCase.getNewsUsecase.getNews(
                    sources = listOf("bbc-news", "abc-news", "al-jazeera-english")
                ).cachedIn(viewModelScope)
                searchState.value=SearchState( pagingData = news)
            }

        }

    }
}