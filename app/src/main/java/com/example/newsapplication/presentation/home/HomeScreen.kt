package com.example.newsapplication.presentation.home

import android.util.Log
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.newsapplication.data.ResponseValue
import com.example.newsapplication.data.remote.pojo.Article
import com.example.newsapplication.presentation.common.ErrorPage

@Composable
fun HomeScreen(navController: NavHostController, lazyArticles: LazyPagingItems<Article>) {
    val finalLoadState=if(
        lazyArticles.loadState.append is LoadState.Loading||
        lazyArticles.loadState.refresh is LoadState.Loading||
        lazyArticles.loadState.prepend is LoadState.Loading){
        ResponseValue.Loading(true)
    }
    else if(
        lazyArticles.loadState.append is LoadState.Error||
        lazyArticles.loadState.refresh is LoadState.Error||
        lazyArticles.loadState.prepend is LoadState.Error){
        val error=if(lazyArticles.loadState.prepend is LoadState.Error)
            (lazyArticles.loadState.prepend as LoadState.Error).error
        else if(lazyArticles.loadState.refresh is LoadState.Error)
            (lazyArticles.loadState.refresh as LoadState.Error).error
        else
            (lazyArticles.loadState.append as LoadState.Error).error
        ResponseValue.Error(error = error)
    }
    else
        ResponseValue.Success(lazyArticles)

    when(finalLoadState){
        is ResponseValue.Loading->{
            Log.e("status","loading")

            LoadingLayout()


        }
        is ResponseValue.Error->{
            Log.e("status","error")
            ErrorPage(finalLoadState.error)

        }
        is ResponseValue.Success->{
            Log.e("status","success")

            NewsListing(finalLoadState.data!!)
        }
    }

}

@Composable
fun NewsListing(data: LazyPagingItems<Article>) {
  LazyColumn(){
      items(data.itemCount){
          ArticleCard(modifier = Modifier.fillMaxWidth(), article = data[it]!!) {

          }
      }
  }
}
@Composable
fun LoadingLayout() {
    Column (modifier = Modifier.fillMaxSize()){

            Box(modifier = Modifier.size(100.dp).shimmer())

    }

}


