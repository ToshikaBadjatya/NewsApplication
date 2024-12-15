package com.example.newsapplication.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newsapplication.data.remote.pojo.Article

@Database(entities = [Article::class], version = 1, exportSchema = false)
@TypeConverters(NewsTypeConverters::class)
abstract class ArticleDb:RoomDatabase() {
    abstract fun articleDao(): ArticleDao

    companion object {
        @Volatile
        private var INSTANCE: ArticleDb? = null

        fun getInstance(context: Context): ArticleDb {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ArticleDb::class.java,
                    "article_database"
                ).addTypeConverter(NewsTypeConverters())
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}