package com.example.newsapplication.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.newsapplication.presentation.onboarding.Dimens

@Composable
fun Indicator(modifier: Modifier,pageSize:Int, currentPage:Int,onClick:(Int)->Unit) {
    Row(modifier){
        repeat(pageSize){
            Box(modifier=Modifier.padding(4.dp).size(Dimens.IndicatorSize).clip(CircleShape).background(
                if(currentPage==it)MaterialTheme.colorScheme.primary else Color.Gray
            ).clickable { onClick.invoke(it) })
        }
    }
}