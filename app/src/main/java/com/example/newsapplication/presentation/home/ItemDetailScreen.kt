package com.example.newsapplication.presentation.home

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.newsapplication.R
import com.example.newsapplication.data.remote.pojo.Article
import com.example.newsapplication.intent.DetailEvents
import com.example.newsapplication.presentation.Dimens

@Composable
fun ItemDetailScreen(article: Article,navigateUp: () -> Unit,onEvent:(DetailEvents)->Unit) {
    val context = LocalContext.current
    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(
            onBackClicked = { navigateUp.invoke() },
            onBookMarkClick = {
               onEvent.invoke(DetailEvents.SaveItem(article))
            },
            onShareClick = {
                val intent = Intent(Intent.ACTION_SEND)
                intent.type ="text/plain"
                intent.putExtra(Intent.EXTRA_TEXT,article.url)
                if (intent.resolveActivity(context.packageManager) != null) {
                    context.startActivity(intent)
                }
                           },
            onBrowseClick = {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(article.url)
                if (intent.resolveActivity(context.packageManager) != null) {
                    context.startActivity(intent)
                }
            })
        ItemDetail(article = article)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    onBackClicked: () -> Unit,
    onBookMarkClick: () -> Unit,
    onShareClick: () -> Unit,
    onBrowseClick: () -> Unit
) {
    TopAppBar(title = { },
        modifier = Modifier.fillMaxWidth(),
        navigationIcon = {
            Icon(
                modifier = Modifier
                    .padding(start = Dimens.DefaultPadding)
                    .clickable { onBackClicked.invoke() },
                painter = painterResource(id = R.drawable.ic_back_arrow),
                contentDescription = "back icon"
            )
        },
        actions = {
            Icon(
                painter = painterResource(id = R.drawable.ic_bookmark),
                contentDescription = "back icon",
                modifier = Modifier.padding(end = Dimens.DefaultPadding),
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "back icon",
                modifier = Modifier.padding(end = Dimens.DefaultPadding),
            )

            Icon(
                painter = painterResource(id = R.drawable.ic_network),
                contentDescription = "back icon",
                modifier = Modifier.padding(end = Dimens.DefaultPadding),
            )

        }
    )

}

@Composable
fun ItemDetail(article: Article) {
    val ctx = LocalContext.current
    LazyColumn(
        modifier = Modifier.padding(
            start = Dimens.MediumPadding2,
            top = Dimens.MediumPadding2, end = Dimens.MediumPadding2
        )
    ) {
        item {
            Column {

                AsyncImage(
                    modifier = Modifier
                        .size(Dimens.ArticleImageHeight)
                        .clip(
                            RoundedCornerShape(20.dp)
                        )
                        .padding(vertical = Dimens.DefaultPadding),

                    model = ImageRequest.Builder(ctx).data(article.urlToImage).build(),
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds
                )
            }
            Text(text = article.title,
                style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(Dimens.DefaultPaddingSmall))
            Text(text = article.content,
                style = MaterialTheme.typography.bodyLarge)
        }
    }

}