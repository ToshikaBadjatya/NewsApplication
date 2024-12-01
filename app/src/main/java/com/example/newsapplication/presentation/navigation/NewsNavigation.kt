package com.example.newsapplication.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsapplication.presentation.home.HomeScreen
import com.example.newsapplication.presentation.viewmodel.NewsViewModel

@Composable
fun NewsNavigation(modifier: Modifier) {
    val navController= rememberNavController()

    NavHost(navController =navController , modifier =modifier, startDestination = Route.HomeScreen.routeName ){

        composable(route=Route.HomeScreen.routeName){
            val newsViewModel:NewsViewModel= hiltViewModel()
            HomeScreen(navController,newsViewModel.news.collectAsLazyPagingItems())
        }

    }

}
