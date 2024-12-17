package com.example.newsapplication.presentation.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newsapplication.R
import com.example.newsapplication.data.remote.pojo.Article
import com.example.newsapplication.intent.DetailEvents
import com.example.newsapplication.intent.NewsEvents
import com.example.newsapplication.presentation.home.BookMarkScreen
import com.example.newsapplication.presentation.home.HomeScreen
import com.example.newsapplication.presentation.home.ItemDetailScreen
import com.example.newsapplication.presentation.viewmodel.BookMarkState
import com.example.newsapplication.presentation.viewmodel.BookMarkViewModel
import com.example.newsapplication.presentation.viewmodel.NewsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeNavigation(modifier: Modifier) {
    val context = LocalContext.current
    val navItems = listOf(
        context.getString(R.string.Home),
        context.getString(R.string.Bookmark),
    )
    var selectedIndex by rememberSaveable {
        mutableStateOf<Int>(0)
    }
    val navController = rememberNavController()

    val startDestination by remember {
        derivedStateOf<String> {
            if (selectedIndex == 0) {
                Route.HomeScreen.routeName
            } else {
                Route.BookMarkScreen.routeName
            }
        }
    }
    val icons = listOf(R.drawable.ic_home, R.drawable.ic_bookmark)
    Scaffold(
        modifier = modifier,
        bottomBar = {
            BottomNavigationBar(items = navItems, icons, selectedIndex = selectedIndex) {
                selectedIndex = it
            }
        }
    ) {
        NewsNavigation(modifier = modifier.padding(it), navController, startDestination)
    }

}

@Composable
fun NewsNavigation(modifier: Modifier, navController: NavHostController, start: String) {


    Column(modifier = modifier) {
        NavHost(
            modifier = Modifier.fillMaxHeight(),
            navController = navController,
            startDestination = start
        ) {

            composable(route = Route.HomeScreen.routeName) {
                val newsViewModel: NewsViewModel = hiltViewModel()
                if (newsViewModel._searchText.isEmpty()) {
                    newsViewModel.onEvent(NewsEvents.GetNews)
                }
                HomeScreen(searchState = newsViewModel._searchState.value,
                    newsEvent = {
                        newsViewModel.onEvent(it)
                    }, goToDetail = {
                       goToDetail(it,navController,false)
                    })
            }
            composable(route = Route.DetailScreen.routeName) {
                val bookMarkViewModel: BookMarkViewModel = hiltViewModel()
                val article =
                    navController.previousBackStackEntry?.savedStateHandle?.get("article") as? Article
                val bookMarkedAlready= navController.previousBackStackEntry?.savedStateHandle?.get("bookMarkedAlready") as? Boolean?:false
                if (article != null) {
                    ItemDetailScreen(
                        article = article,
                        navigateUp = { navController.navigateUp() },
                        bookMarkedAlready = bookMarkedAlready,
                        onEvent = {
                            bookMarkViewModel.onEvent(it)
                        }
                    )
                }
            }
            composable(route = Route.BookMarkScreen.routeName) {
                val bookMarkViewModel: BookMarkViewModel = hiltViewModel()
                bookMarkViewModel.onEvent(DetailEvents.GetItems)
                BookMarkScreen(bookMarkViewModel._bookMarkState.value){
                    goToDetail(it,navController,true)
                }
            }
        }

    }

//    BottomNavigationBar(navItems,Modifier.fillMaxHeight(0.1f))


}
fun goToDetail(article: Article,navController: NavHostController,bookMarkedAlready:Boolean){
    navController.currentBackStackEntry?.savedStateHandle?.set("article", article)
    navController.currentBackStackEntry?.savedStateHandle?.set("bookMarkedAlready", bookMarkedAlready)
    navController.navigate(Route.DetailScreen.routeName)
}

@Composable
fun BottomNavigationBar(
    items: List<String>,
    icons: List<Int>,
    selectedIndex: Int,
    onClick: (Int) -> Unit
) {

    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.background,

        ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = icons[index]),
                        contentDescription = ""
                    )
                },
                label = { Text(item) },
                selected = selectedIndex == index,
                onClick = { onClick.invoke(index) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    indicatorColor = MaterialTheme.colorScheme.background,
                    unselectedIconColor = colorResource(id = R.color.body),
                    unselectedTextColor = colorResource(id = R.color.body),
                )
            )
        }
    }
}

