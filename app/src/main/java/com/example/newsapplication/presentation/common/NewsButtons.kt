package com.example.newsapplication.presentation.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.newsapplication.presentation.Dimens

@Composable
fun NewsButtons(modifier: Modifier,
                textLeft:String,textRight:String,
                onLeftClick:()->Unit,onRightClick:()->Unit) {
    Row(modifier, verticalAlignment = Alignment.CenterVertically){
        Text(textLeft,modifier=Modifier.clickable { onLeftClick.invoke() }, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.width(Dimens.DefaultPaddingSmall))
        Button(onClick = { onRightClick.invoke()},modifier = Modifier.padding(start=4.dp)) {
            Text(textRight,style = MaterialTheme.typography.bodyLarge)
        }
    }
}