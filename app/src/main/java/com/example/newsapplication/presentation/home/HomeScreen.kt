package com.example.newsapplication.presentation.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.newsapplication.data.remote.pojo.Article
import com.example.newsapplication.presentation.common.ErrorPage

@Composable
fun HomeScreen(navController: NavHostController, lazyArticles: LazyPagingItems<Article>) {
    val error = if (
        lazyArticles.loadState.append is LoadState.Error)
        (lazyArticles.loadState.append as LoadState.Error).error
    else if (lazyArticles.loadState.refresh is LoadState.Error)
        (lazyArticles.loadState.refresh as LoadState.Error).error
    else if (lazyArticles.loadState.prepend is LoadState.Error)
        (lazyArticles.loadState.prepend as LoadState.Error).error
    else null
    when {
        error != null -> {
            ErrorPage(error = error)
        }

        (lazyArticles.loadState.refresh is LoadState.Loading
                || lazyArticles.loadState.append is LoadState.Loading
                || lazyArticles.loadState.prepend is LoadState.Loading) -> {
            LoadingLayout()
        }

        else -> {
            NewsListing(data = lazyArticles)
        }
    }


}

@Composable
fun NewsListing(data: LazyPagingItems<Article>) {
    LazyColumn() {
        items(data.itemCount) {
            ArticleCard(modifier = Modifier.fillMaxWidth(), article = data[it]!!) {

            }
        }
    }
}

@Composable
fun LoadingLayout() {

       Column {
           repeat(10){
               ShimmerBox(modifier  = Modifier.fillMaxWidth() )
           }
       }




}


