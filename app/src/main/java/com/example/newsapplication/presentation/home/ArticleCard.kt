package com.example.newsapplication.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.request.ImageRequest
import com.example.newsapplication.data.remote.pojo.Article
val LocalElevations = compositionLocalOf { 1 }
@Composable
fun ArticleCard(modifier: Modifier,article: Article,onClick:()->Unit) {
    val ctx=LocalContext.current

    Row (modifier=modifier){
        AsyncImage(model = ImageRequest.Builder(ctx).data(article.urlToImage).build(), contentDescription = "",
            contentScale = ContentScale.FillBounds)
        Column {

        }
    }
}

fun child() {
}
