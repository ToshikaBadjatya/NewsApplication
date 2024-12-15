package com.example.newsapplication.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsapplication.R
import com.example.newsapplication.data.remote.pojo.Article
import com.example.newsapplication.intent.NewsEvents
import com.example.newsapplication.presentation.Dimens
import com.example.newsapplication.presentation.common.ErrorPage
import com.example.newsapplication.presentation.navigation.Route
import com.example.newsapplication.presentation.viewmodel.SearchState


@Composable
fun HomeScreen(

    searchState: SearchState,
    newsEvent: (NewsEvents)->Unit,
    goToDetail: (Article)->Unit,
) {
    var searchText by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Icon(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "top icon",
            tint = MaterialTheme.colorScheme.primary
        )
        SearchBar(modifier = Modifier
            .fillMaxWidth()
            .padding(Dimens.DefaultPaddingSmall),
            searchText = searchText,
            onSearch = {newsEvent.invoke(NewsEvents.SearchEvent(searchText))},
            onTextChange = { searchText = it })
             searchState?.pagingData?.let {
                 val lazyArticles=it.collectAsLazyPagingItems()
                 HomeScreenData(lazyArticles = lazyArticles,goToDetail)
            }



    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    modifier: Modifier,
    searchText: String,
    onTextChange: (String) -> Unit,
    onSearch: () -> Unit
) {
    TextField(
        searchText, { onTextChange.invoke(it) },
        modifier
            .border(
                shape = RoundedCornerShape(10.dp),
                width = 3.dp,
                color = MaterialTheme.colorScheme.primary
            )
            .background(Color.White),
        placeholder = {
                      Text(text = "Search", style = MaterialTheme.typography.bodyLarge)
        },
        trailingIcon = {
              Icon(painter = painterResource(id = R.drawable.ic_search) , contentDescription ="" ,
                  modifier=Modifier.clickable { onSearch.invoke() })
            
        },

        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = {onSearch.invoke()}),
        singleLine = true
    )
}

@Composable
fun HomeScreenData(lazyArticles: LazyPagingItems<Article>?, goToDetail: (Article) -> Unit) {
    if(lazyArticles==null) return
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
            NewsListing(data = lazyArticles,goToDetail)
        }
    }


}

@Composable
fun NewsListing(data: LazyPagingItems<Article>,onItemClick:(Article)->Unit) {
    LazyColumn() {
        items(data.itemCount) {
            ArticleCard(modifier = Modifier.fillMaxWidth(), article = data[it]!!) {
              onItemClick.invoke(it)
            }
        }
    }
}

@Composable
fun LoadingLayout() {

    Column {
        repeat(10) {
            ShimmerBox(modifier = Modifier.fillMaxWidth())
        }
    }


}



