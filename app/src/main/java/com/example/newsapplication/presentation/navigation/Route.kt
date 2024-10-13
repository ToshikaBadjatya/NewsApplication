package com.example.newsapplication.presentation.navigation

sealed class Route(val routeName:String) {
    object NewsNavigation:Route("news_navigation_route")

    object OnBoardingRoute:Route("onboarding_route")
}