package com.example.newsapplication.intent

sealed class OnBoardingEvent {
    object SaveAppEntry: OnBoardingEvent()
}