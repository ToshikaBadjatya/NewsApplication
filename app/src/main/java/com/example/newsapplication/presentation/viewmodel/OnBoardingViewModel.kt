package com.example.newsapplication.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapplication.domain.usecases.EntryUseCases
import com.example.newsapplication.intent.OnBoardingEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class OnBoardingViewModel @Inject constructor(private val entryUseCase:EntryUseCases):ViewModel() {
    fun onEvent(event:OnBoardingEvent){
        when(event){
            is OnBoardingEvent.SaveAppEntry->{
                viewModelScope.launch {
                    entryUseCase.write.setUserEntry()
                }
            }
        }
    }
}