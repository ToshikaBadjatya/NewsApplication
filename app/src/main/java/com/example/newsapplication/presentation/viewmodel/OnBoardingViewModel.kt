package com.example.newsapplication.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.ActivityNavigator
import com.example.newsapplication.domain.usecases.EntryUseCases
import com.example.newsapplication.intent.OnBoardingEvent
import com.example.newsapplication.presentation.navigation.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(private val entryUseCase: EntryUseCases) :
    ViewModel() {
    private val showSplash = mutableStateOf<Boolean>(true)
    val _showSplash
        get() = showSplash
    val startScreen = mutableStateOf<String>(Route.OnBoardingRoute.routeName)
    init {
        entryUseCase.read.getUserEntry().onEach{startFromSplash->
            if(startFromSplash){
                startScreen.value=Route.OnBoardingRoute.routeName
            }else{
                startScreen.value=Route.NewsNavigation.routeName
            }
            delay(3000)
            showSplash.value=false
        }.launchIn(viewModelScope)
    }


    fun onEvent(event: OnBoardingEvent) {
        when (event) {
            is OnBoardingEvent.SaveAppEntry -> {
                saveAppEntry()
            }
        }
    }

    private fun saveAppEntry() {
        viewModelScope.launch {
            entryUseCase.write.setUserEntry()
        }
    }

}