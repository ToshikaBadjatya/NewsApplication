package com.example.newsapplication.intent

sealed class OnBoardingEvent {
    object SaveAppEntry: OnBoardingEvent()
}

sealed class NewsEvents{
    object SearchEvent : NewsEvents()
    object GetNews:NewsEvents()
    class UpdateSearchText(val searchText:String):NewsEvents()
}
