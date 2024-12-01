package com.example.newsapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.newsapplication.presentation.navigation.Navgraph
import com.example.newsapplication.presentation.viewmodel.OnBoardingViewModel
import com.example.newsapplication.ui.theme.NewsApplicationTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val onBoardingViewModel: OnBoardingViewModel by viewModels<OnBoardingViewModel> ()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            //keep splash visible until start is fetched
           installSplashScreen().apply {
               setKeepOnScreenCondition{
                   (onBoardingViewModel._showSplash.value)
               }
           }


        setContent {

            NewsApplicationTheme {
                val systemController= rememberSystemUiController()

                systemController.setNavigationBarColor(Color.Transparent, isSystemInDarkTheme())
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppData(Modifier.fillMaxSize(),onBoardingViewModel)
                 }
            }
        }
    }
}

@Composable
fun AppData(modifier: Modifier, start: OnBoardingViewModel) {
  Navgraph(modifier,start)
}

