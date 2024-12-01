package com.example.newsapplication.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.newsapplication.domain.usecases.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(val newsUseCase: NewsUseCase):ViewModel() {
}