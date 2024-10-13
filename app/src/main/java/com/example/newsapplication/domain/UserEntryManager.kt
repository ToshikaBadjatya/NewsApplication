package com.example.newsapplication.domain

import kotlinx.coroutines.flow.Flow

interface UserEntryManager {
    suspend fun  setUserEntry()
    fun getUserEntry(): Flow<Boolean>
}