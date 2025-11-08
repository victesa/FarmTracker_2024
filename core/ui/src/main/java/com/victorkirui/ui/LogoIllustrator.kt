package com.victorkirui.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun IllustratorLogo(logo: Int, paddingTop: Dp, heightPercentage: Float){
    Column(modifier = Modifier
        .padding(top = paddingTop)
        .fillMaxWidth()
        .fillMaxHeight(heightPercentage),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Image(painter = painterResource(id = logo),
            contentDescription = null, modifier = Modifier
                .height(350.dp)
                .width(300.dp) )
    }

}