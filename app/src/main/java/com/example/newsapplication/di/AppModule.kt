package com.example.newsapplication.di

import android.app.Application
import com.example.newsapplication.Constants
import com.example.newsapplication.data.local.ArticleDao
import com.example.newsapplication.data.local.ArticleDb
import com.example.newsapplication.data.manager.GetNewsImpl
import com.example.newsapplication.data.manager.LocalUserManagerImpl
import com.example.newsapplication.data.remote.api.GetNewsApi
import com.example.newsapplication.domain.GetNewsRepository
import com.example.newsapplication.domain.LocalUserManager
import com.example.newsapplication.domain.usecases.EntryUseCases
import com.example.newsapplication.domain.usecases.GetNewsUsecase
import com.example.newsapplication.domain.usecases.NewsUseCase
import com.example.newsapplication.domain.usecases.ReadEntryUseCases
import com.example.newsapplication.domain.usecases.SearchNewsUsecase
import com.example.newsapplication.domain.usecases.WriteEntryUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun getEntryUseCase(entry: LocalUserManager): EntryUseCases {
        return EntryUseCases(ReadEntryUseCases(entry), WriteEntryUseCases(entry))
    }

    @Provides
    @Singleton
    fun getUserEntryManager(application: Application): LocalUserManager {
        return LocalUserManagerImpl(application)
    }
    @Provides
    @Singleton
    fun getNewsUseCase(newsRepo: GetNewsRepository): NewsUseCase {
        return NewsUseCase(GetNewsUsecase(newsRepo), SearchNewsUsecase(newsRepo))
    }
    @Provides
    @Singleton
    fun getNewsRepo(newsApi: GetNewsApi): GetNewsRepository {
        return GetNewsImpl(newsApi)
    }
    @Provides
    @Singleton
    fun getNewsApi(okHttpClient: OkHttpClient): GetNewsApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(GetNewsApi::class.java)
    }
    @Provides
    @Singleton
    fun getOkkHttpClient():OkHttpClient{
        val interceptor=HttpLoggingInterceptor().apply {
            level=HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient().newBuilder().addInterceptor(interceptor).build()
    }
    @Provides
    @Singleton
    fun getNewsDao(application: Application): ArticleDao {
        return ArticleDb.getInstance(application).articleDao()
    }









}