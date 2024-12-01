package com.example.newsapplication.presentation.home

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.request.ImageRequest
import com.example.newsapplication.R
import com.example.newsapplication.data.remote.pojo.Article
import com.example.newsapplication.data.remote.pojo.Source
import com.example.newsapplication.presentation.Dimens

@Composable
fun ArticleCard(modifier: Modifier, article: Article, onClick: () -> Unit) {
    val ctx = LocalContext.current

    Row(modifier = modifier.height(Dimens.ArticleCardSize)
        .padding(Dimens.DefaultPadding)
        .clickable { onClick.invoke() }) {

        AsyncImage(
            modifier = Modifier
                .size(Dimens.ArticleCardSize)
                .clip(
                    RoundedCornerShape(20.dp)
                ),

            model = ImageRequest.Builder(ctx).data(article.urlToImage).build(),
            contentDescription = "",
            contentScale = ContentScale.FillBounds
        )



        Column(
            modifier = Modifier
                .padding(Dimens.DefaultPadding), verticalArrangement = Arrangement.SpaceAround
        ) {

               Text(
                   modifier = Modifier, text = article.title,
                   style = MaterialTheme.typography.bodyMedium,
                   fontWeight = FontWeight.Bold,
                   maxLines = 1,
                   overflow = TextOverflow.Ellipsis
               )
              Spacer(modifier = Modifier.height(10.dp))

            Text(
                modifier = Modifier, text = article.description,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun ShimmerBox(modifier: Modifier, content: @Composable () -> Unit) {
    Box(modifier = modifier.shimmer()) {
        content
    }
}

fun Modifier.shimmer(): Modifier = composed {
    val transition = rememberInfiniteTransition()
    val values = transition.animateFloat(
        initialValue = 0.3f, targetValue = 0.9f,
        animationSpec = infiniteRepeatable(animation = tween(1000), repeatMode = RepeatMode.Reverse)
    ).value
    this.background(color = colorResource(id = R.color.shimmer).copy(values))

    return@composed this
}

@Preview(showBackground = true)
@Composable
fun showPreview() {
    val article = Article(
        author = "",
        content = "",
        description = "saxsvdgbthnjymmukn",
        publishedAt = "2 hours",
        source = Source(id = "", name = "BBC"),
        title = "Her train broke down. Her phone died. And then she met her Saver in a",
        url = "",
        urlToImage = "https://ichef.bbci.co.uk/live-experience/cps/624/cpsprodpb/11787/production/_124395517_bbcbreakingnewsgraphic.jpg"

    )

    ArticleCard(modifier = Modifier.fillMaxSize(), article = article) {

    }
}

