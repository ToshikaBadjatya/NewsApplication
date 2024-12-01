package com.example.newsapplication.presentation.navigation

sealed class Route(val routeName:String) {
    object NewsNavigation:Route("news_navigation_route")
    object OnBoardingRoute:Route("onboarding_route")
    object HomeScreen:Route("home_route")
    object SearchScreen:Route("search_route")
    object DetailScreen:Route("detail_route")
    object BookMarkScreen:Route("bookmark_route")
}