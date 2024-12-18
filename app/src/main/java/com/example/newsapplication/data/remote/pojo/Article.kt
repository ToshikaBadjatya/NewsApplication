package com.example.newsapplication.data.remote.pojo

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.io.Serializable
@Parcelize
data class NewsResponse(
    val articles: List<Article>,
//    val status: String,
    val totalResults: Int
):Parcelable
@Parcelize
@Entity(tableName = "article_database")
data class Article(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    @PrimaryKey val url: String,
    val urlToImage: String
) : Parcelable

@Parcelize
data class Source(
    val id: String,
    val name: String
) : Parcelable