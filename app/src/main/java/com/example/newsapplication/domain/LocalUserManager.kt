package com.example.newsapplication.domain

import kotlinx.coroutines.flow.Flow

interface LocalUserManager {
    suspend fun  setUserEntry()
    fun getUserEntry(): Flow<Boolean>
}