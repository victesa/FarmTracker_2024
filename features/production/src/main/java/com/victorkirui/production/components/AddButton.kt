package com.victorkirui.production.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit

@Composable
fun AddButton(fontSize: TextUnit, paddingText: Dp){
    Button(onClick = { /*TODO*/ },
        modifier = Modifier.fillMaxWidth()) {
        Text(text = "Add Production", fontSize = fontSize,
            modifier = Modifier
                .padding(paddingText))
    }
}