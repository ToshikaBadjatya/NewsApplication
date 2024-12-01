package com.example.newsapplication.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newsapplication.data.remote.api.GetNewsApi
import com.example.newsapplication.data.remote.pojo.Article

class NewsPagingSource(private val newsApi: GetNewsApi, val sources: String):PagingSource<Int,Article>() {
    private var totalObtained=0
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        try {
            // Start refresh at page 1 if undefined.
            val nextPageNumber = params.key ?: 1
            val response = newsApi.getNewsApi(
                page = nextPageNumber,
                sources =sources,)
            totalObtained += response.articles.size
            val total=response.totalResults
            return LoadResult.Page(
                data = response.articles,
                prevKey = null, // Only paging forward.
                nextKey = if(total<=totalObtained) nextPageNumber+1 else null
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}