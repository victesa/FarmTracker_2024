package com.victorkirui.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GreenButton(onClick:() -> Unit, fontSize: TextUnit, text: String){
    Button(onClick = onClick, modifier = Modifier
        .fillMaxWidth()
        .height(55.dp)) {
        Text(text = text, fontSize = fontSize)
    }

}

@Composable
@Preview
internal fun Preview(){
    GreenButton(onClick = { /*TODO*/ }, fontSize = 16.sp, text = "Log In")
}