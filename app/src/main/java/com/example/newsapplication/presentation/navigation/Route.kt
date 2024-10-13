package com.example.newsapplication.presentation.navigation

sealed class Route(val routeName:String) {
    object OnBoardingRoute:Route("onboarding_route")
}