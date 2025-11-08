package com.victorkirui.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TitleParagraph(maxHeight: Float, style: TextStyle, fontSize: TextUnit){
    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(maxHeight)) {
        Text(text = "Farm Tracker",
            style = style, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 24.dp))
        Text(text = "Your focus is on one place and you can supervise your work with ease",
            color = Color.DarkGray,
            style = MaterialTheme.typography.bodyLarge, textAlign = TextAlign.Center,
            fontStyle = FontStyle(R.font.inter_regular), fontSize = fontSize
        )
    }
}