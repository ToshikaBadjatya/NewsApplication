package com.example.newsapplication.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.newsapplication.data.remote.pojo.Source

@ProvidedTypeConverter
class NewsTypeConverters {
    @TypeConverter
    fun convertSourceToString(source: Source):String{
        return "${source.id},${source.name}"
    }

    @TypeConverter
    fun convertStringToSource(source: String):Source{
        return source.split(',').let { sourceArray ->
            Source(id = sourceArray[0], name = sourceArray[1])
        }
    }

}