package com.example.newsapplication.data.remote.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newsapplication.data.remote.api.GetNewsApi
import com.example.newsapplication.data.remote.pojo.Article
import kotlinx.coroutines.delay

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
            Log.e("load","called $nextPageNumber")
            val response = newsApi.getNewsApi(
                page = nextPageNumber,
                sources = sources,
            )
            if (response.body() != null) {
                val obj = response.body()!!
                totalObtained += obj.articles.size
                val total = obj.totalResults
                val data=obj.articles.distinctBy { it.title }
                delay(2000)
                return LoadResult.Page(
                    data = data,
                    prevKey = null, // Only paging forward.
                    nextKey = if (total <= totalObtained) nextPageNumber + 1 else null
                )
            }
            else{
                return LoadResult.Error(
                    Exception(response.message())
                )
            }

        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}
class SearchNewsPagingSource(private val newsApi: GetNewsApi, val sources: String,val keyword:String):PagingSource<Int,Article>() {
    private var totalObtained=0
    init {
        Log.e("searchEvent","searchResponse created ")
    }


    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        Log.e("searchEvent","${state.anchorPosition}")

        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        Log.e("searchEvent","searchResponse called ${params.key}")

        try {
            // Start refresh at page 1 if undefined.

            val nextPageNumber = params.key ?: 1
            val response = newsApi.searchNewsApi(
                page = nextPageNumber,
                sources = sources,
                keyWord = keyword
            )
            Log.e("searchEvent","searchResponse ${response.body()} ")
            if (response.body() != null) {

                val obj = response.body()!!
                totalObtained += obj.articles.size
                val total = obj.totalResults
                val data=obj.articles.distinctBy { it.title }
                delay(2000)
                return LoadResult.Page(
                    data = data,
                    prevKey = null, // Only paging forward.
                    nextKey = if (total <= totalObtained) nextPageNumber + 1 else null
                )
            }
            else{
                return LoadResult.Error(
                    Exception(response.message())
                )
            }

        } catch (e: Exception) {
            Log.e("searchEvent","searchResponse ${e} ")

            return LoadResult.Error(e)
        }
    }
}