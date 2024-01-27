package com.victorkirui.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun IndeterminateProgressIndicator(){
    Dialog(onDismissRequest = {} ) {
        CircularProgressIndicator(
            modifier = Modifier
                .background(Color.Gray)
                .padding(8.dp)
                .size(48.dp),
            color = MaterialTheme.colorScheme.secondaryContainer,
            trackColor = MaterialTheme.colorScheme.primary
        )
    }

}

@Composable
@Preview
internal fun PreviewTwo(){
    IndeterminateProgressIndicator()
}