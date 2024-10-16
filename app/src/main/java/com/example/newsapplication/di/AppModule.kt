package com.example.newsapplication.di

import android.app.Application
import android.os.UserManager
import com.example.newsapplication.data.manager.UserEntryImpl
import com.example.newsapplication.domain.UserEntryManager
import com.example.newsapplication.domain.usecases.EntryUseCases
import com.example.newsapplication.domain.usecases.ReadEntryUseCases
import com.example.newsapplication.domain.usecases.WriteEntryUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    fun getUserEntryManager(application: Application):UserEntryManager{
        return UserEntryImpl(application)
    }
    @Provides
    fun getEntryUseCase(entry:UserEntryImpl):EntryUseCases{
        return EntryUseCases(ReadEntryUseCases(entry), WriteEntryUseCases(entry))
    }
}