package com.victorkirui.production.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit

@Composable
internal fun ScreenTitle(fontSize: TextUnit, heightPercentage: Float){
    Column(modifier = Modifier.fillMaxWidth()
        .fillMaxHeight(heightPercentage),
        verticalArrangement = Arrangement.Center) {
        Text(text = "Production", fontSize = fontSize,
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold)
    }
}