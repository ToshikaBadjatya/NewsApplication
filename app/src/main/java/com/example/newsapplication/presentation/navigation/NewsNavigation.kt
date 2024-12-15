package com.example.newsapplication.presentation.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newsapplication.R
import com.example.newsapplication.intent.NewsEvents
import com.example.newsapplication.presentation.home.HomeScreen
import com.example.newsapplication.presentation.viewmodel.NewsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeNavigation(modifier: Modifier){
    val context= LocalContext.current
    val navItems=listOf(context.getString(R.string.Home),
        context.getString(R.string.Bookmark),
    )
    Scaffold(
        modifier = modifier,
        bottomBar ={
            BottomNavigationBar(items = navItems, modifier = Modifier.fillMaxWidth())
        }
    ) {
        NewsNavigation(modifier = modifier.padding(it) )
    }

}
@Composable
fun NewsNavigation(modifier: Modifier) {

    val navController= rememberNavController()
    Column(modifier=modifier) {


        NavHost(
            modifier = Modifier.fillMaxHeight(),
            navController = navController,
            startDestination = Route.HomeScreen.routeName
        ) {

            composable(route = Route.HomeScreen.routeName) {
                val newsViewModel: NewsViewModel = hiltViewModel()
               if(newsViewModel._searchText.isEmpty()){
                  newsViewModel.onEvent(NewsEvents.GetNews)
                }
                HomeScreen(navController, newsViewModel._searchState.value){
                    newsViewModel.onEvent(it)
                }
            }
//            composable(route=Route.DetailScreen.routeName){
////               ItemDetailScreen(navigateUp = {navController.navigateUp()})
//            }
        }

    }

//    BottomNavigationBar(navItems,Modifier.fillMaxHeight(0.1f))


}

@Composable
fun BottomNavigationBar(items: List<String>, modifier: Modifier){
    var selectedIndex by remember{
        mutableStateOf<Int>(0)
    }
    NavigationBar(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.background
    ) {
       items.forEachIndexed { index, item ->
           NavigationBarItem(
               icon = {},
//               icon = { Icon(imageVector = icons[index], contentDescription =item ) },
               label = { Text(item) },
               selected = selectedIndex == index,
               onClick = { selectedIndex = index }
           )
       }
}}

