package com.example.newsapplication.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.paging.compose.LazyPagingItems
import com.example.newsapplication.R
import com.example.newsapplication.data.remote.pojo.Article
import com.example.newsapplication.presentation.Dimens
import com.example.newsapplication.presentation.viewmodel.BookMarkState

@Composable
fun BookMarkScreen(_bookMarkState: BookMarkState,goToDetail:(Article)->Unit) {
    val articles=_bookMarkState.articles
    Column(modifier= Modifier
        .fillMaxSize()
        .padding(
            start = Dimens.MediumPadding1,
            end = Dimens.MediumPadding1, top = Dimens.MediumPadding1
        )) {
        Text(text = "Bookmark",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
        if(articles.isEmpty()){
            Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                Icon(painter = painterResource(id = R.drawable.ic_search_document), contentDescription = "no item",tint= Color.LightGray)
                Text(text = "No Saved BookMarks", color = Color.LightGray)
            }
        }
        else{
            LazyColumn() {
                items(articles.size) {
                    ArticleCard(modifier = Modifier.fillMaxWidth(), article = articles[it]!!) {
                        goToDetail.invoke(it)
                    }
                }
            }
        }

    }
}
