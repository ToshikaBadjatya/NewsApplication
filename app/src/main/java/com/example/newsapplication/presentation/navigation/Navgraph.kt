package com.example.newsapplication.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newsapplication.presentation.onboarding.OnboardingScreen

@Composable
fun Navgraph( modifier: Modifier){
    val navController=rememberNavController()
    NavHost(navController =navController , modifier =modifier, startDestination = Route.OnBoardingRoute.routeName  ){
      composable(route=Route.OnBoardingRoute.routeName){
          OnboardingScreen(navController)
      }
    }
}