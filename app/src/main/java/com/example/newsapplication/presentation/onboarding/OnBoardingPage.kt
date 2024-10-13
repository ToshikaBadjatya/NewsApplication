package com.example.newsapplication.presentation.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.newsapplication.presentation.Dimens

@Composable
fun OnBoardingPage(page: MyPage, modifier: Modifier) {
    Column(modifier = modifier,) {
        Image(
            painter = painterResource(id = page.icon), contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f),
            contentScale = ContentScale.FillBounds

        )
        Spacer(modifier = Modifier.height(Dimens.MediumPadding1))
        Text(text = "${page.title}",
            modifier = Modifier.padding(start = Dimens.DefaultPadding,end = Dimens.DefaultPadding ),
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold

                )
        Spacer(modifier = Modifier.height(Dimens.DefaultPadding))
        Text(text = "${page.description}",
            modifier = Modifier.padding(start = Dimens.DefaultPadding,end = Dimens.DefaultPadding ),
                style = MaterialTheme.typography.bodyLarge)
    }
}

@Preview
@Composable
fun MyPreview() {
    val modifier= Modifier
        .fillMaxSize()
//        .verticalScroll(rememberScrollState())
    OnBoardingPage(page = pages[0], modifier =modifier)

}