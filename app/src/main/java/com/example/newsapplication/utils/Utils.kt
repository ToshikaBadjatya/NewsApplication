package com.example.newsapplication.utils

import android.content.Context
import android.net.ConnectivityManager


object Utils{
    fun hasInternet(context: Context):Boolean{

        val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
        return manager?.activeNetworkInfo?.isConnectedOrConnecting ?:false
    }
}