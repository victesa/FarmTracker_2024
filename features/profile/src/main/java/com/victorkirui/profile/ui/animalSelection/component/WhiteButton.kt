package com.victorkirui.profile.ui.animalSelection.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

@Composable
fun WhiteButton(onClick:() -> Unit, text: String, fontSize: TextUnit){
    ElevatedButton(onClick = onClick, modifier = Modifier
        .fillMaxWidth()
        .height(50.dp)) {
        Text(text, color = Color.Black, fontSize = fontSize)
    }
}