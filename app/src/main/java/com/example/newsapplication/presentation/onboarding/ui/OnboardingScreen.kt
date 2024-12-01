package com.example.newsapplication.presentation.onboarding.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.newsapplication.intent.OnBoardingEvent
import com.example.newsapplication.presentation.Dimens
import com.example.newsapplication.presentation.common.Indicator
import com.example.newsapplication.presentation.common.NewsButtons
import com.example.newsapplication.presentation.navigation.Route
import com.example.newsapplication.presentation.onboarding.data.pages
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(navController: NavHostController, callEvent:(event:OnBoardingEvent)->Unit) {
    val pagerState = rememberPagerState(pageCount = {
        pages.size
    })
    val coroutineScope = rememberCoroutineScope()


    val currentPage = remember {
        mutableStateOf<Int>(0)
    }
    Column {
        HorizontalPager(state = pagerState) {
            OnBoardingPage(page = pages[it], modifier = Modifier.fillMaxHeight(0.8f))
        }
        Spacer(modifier = Modifier.padding(Dimens.MediumPadding1))
        currentPage.value = pagerState.currentPage

        Row(modifier=Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Indicator(
                modifier = Modifier.padding(start = Dimens.DefaultPaddingSmall),
                pages.size,
                currentPage.value
            ) {
                coroutineScope.launch { pagerState.scrollToPage(it) }
            }
            var left = ""
            var right = ""
            when (currentPage.value) {
                0 -> {
                    left = "";right = "Next"
                }

                1 -> {
                    left = "Back";right = "Next"
                }
                2 -> {
                    left = "Back";right = "Get Started"
                }
            }

            NewsButtons(modifier = Modifier.padding(end = Dimens.DefaultPaddingSmall), left, right, {
                if (currentPage.value != 0) {
                    coroutineScope.launch { pagerState.scrollToPage(currentPage.value - 1) }
                }
            }, {
                if (currentPage.value != pages.size - 1) {
                    coroutineScope.launch { pagerState.scrollToPage(currentPage.value + 1) }
                } else {
                    callEvent.invoke(OnBoardingEvent.SaveAppEntry)
                    navController.navigate(Route.NewsNavigation.routeName)
                }
            })
        }


    }

}



