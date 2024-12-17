package com.example.newsapplication.intent

import com.example.newsapplication.data.remote.pojo.Article

sealed class OnBoardingEvent {
    object SaveAppEntry: OnBoardingEvent()
}

sealed class NewsEvents{
    object GetNews:NewsEvents()
    class SearchEvent(val searchText:String):NewsEvents()
}
sealed class DetailEvents{
    data class SaveItem(val article: Article):DetailEvents()
    data class DeleteArticle(val article: Article):DetailEvents()

    object GetItems:DetailEvents()
}
