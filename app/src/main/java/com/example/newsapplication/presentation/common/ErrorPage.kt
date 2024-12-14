package com.example.newsapplication.presentation.common

import android.content.Context
import android.util.Log
import androidx.compose.animation.animateColor
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.example.newsapplication.R
import com.example.newsapplication.presentation.Dimens
import com.example.newsapplication.utils.Utils
import java.net.ConnectException
import java.net.SocketTimeoutException

@Composable
fun ErrorPage(error: Throwable?) {
    val context= LocalContext.current
    val infiniteTransition = rememberInfiniteTransition()
    val color by infiniteTransition.animateColor(initialValue = Color.LightGray, targetValue = Color.DarkGray, animationSpec = infiniteRepeatable(repeatMode = RepeatMode.Reverse,
        animation = tween(800)
    ))
   Column(modifier = Modifier.fillMaxSize(),verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
       Icon(painter = painterResource(id = R.drawable.ic_network_error) , contentDescription ="painter" ,
           tint = color
       )
       Spacer(modifier = Modifier.height(Dimens.DefaultPadding))
       Text(text = parseError(error, context = context), style = MaterialTheme.typography.titleMedium,
           color = color
       )
   }
}


fun parseError(error: Throwable?,context: Context): String {
    if(!Utils.hasInternet(context = context))
    return "No Internet"
    return when (error) {

        is SocketTimeoutException -> {
            "Server Unavailable."
        }

        is ConnectException -> {
            "Internet Unavailable."
        }


        else -> {
            "Something went wrong"
        }
    }

}
