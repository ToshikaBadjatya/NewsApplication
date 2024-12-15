package com.example.newsapplication.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newsapplication.intent.OnBoardingEvent
import com.example.newsapplication.presentation.onboarding.ui.OnboardingScreen
import com.example.newsapplication.presentation.viewmodel.OnBoardingViewModel

@Composable
fun Navgraph(modifier: Modifier, onBoardingViewModel: OnBoardingViewModel){
    val navController=rememberNavController()

    NavHost(navController =navController , modifier =modifier, startDestination = onBoardingViewModel.startScreen.value ){

      composable(route=Route.OnBoardingRoute.routeName){
          OnboardingScreen(navController){
              onBoardingViewModel.onEvent(OnBoardingEvent.SaveAppEntry)
          }
      }
        composable(route=Route.NewsNavigation.routeName){
            HomeNavigation(modifier)
        }
    }
}