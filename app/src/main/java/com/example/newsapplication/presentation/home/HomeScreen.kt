package com.example.newsapplication.presentation.home

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.newsapplication.data.ResponseValue
import com.example.newsapplication.data.remote.pojo.Article

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
        ResponseValue.Error()
    }
    else
        ResponseValue.Success(lazyArticles)

    when(finalLoadState){
        is ResponseValue.Loading->{
            Log.e("status","loading")


        }
        is ResponseValue.Error->{
            Log.e("status","error")

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
