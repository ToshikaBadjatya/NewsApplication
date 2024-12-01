package com.example.newsapplication.data

sealed class ResponseValue<T> {

    data class Loading<T>(val isLoading: Boolean) : ResponseValue<T>()

    data class Success<T>(val data: T) : ResponseValue<T>()

    data class Error<T>( val error: Exception?=null) : ResponseValue<T>()
}