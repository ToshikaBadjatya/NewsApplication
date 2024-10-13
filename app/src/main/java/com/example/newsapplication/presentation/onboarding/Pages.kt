package com.example.newsapplication.presentation.onboarding

import androidx.annotation.DrawableRes
import com.example.newsapplication.R

data class MyPage(
    val title: String,
    val description: String,
    @DrawableRes val icon: Int
)

val pages = listOf<MyPage>(
    MyPage(
        "Lorem ipsum dolor sit amet, consectetuer adipiscing elit",
        "penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget,", R.drawable.onboarding1
    ),
    MyPage(
        "Lorem ipsum dolor sit amet, consectetuer adipiscing elit",
        "penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget,", R.drawable.onboarding2
    ),
    MyPage(
        "Lorem ipsum dolor sit amet, consectetuer adipiscing elit",
        "penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget,", R.drawable.onboarding3
    )
)