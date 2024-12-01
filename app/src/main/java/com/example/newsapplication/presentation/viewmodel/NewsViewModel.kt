package com.example.newsapplication.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.newsapplication.domain.usecases.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(val newsUseCase: NewsUseCase):ViewModel() {
    val news = newsUseCase.getNewsUsecase.getNews(
        sources = listOf("bbc-news","abc-news")
    ).cachedIn(viewModelScope)
}