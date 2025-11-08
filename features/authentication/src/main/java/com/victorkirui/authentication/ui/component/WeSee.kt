package com.victorkirui.authentication.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun  weSee(text: String, fontSize: TextUnit){
    AlertDialog(modifier = Modifier.border(width = 1.dp, shape = RoundedCornerShape(20.dp), color = Color.White),
        properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false),
        onDismissRequest = {}) {
        Column(modifier = Modifier
            .fillMaxWidth(.8f)
            .background(Color.White)
            .height(150.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.secondaryContainer,
                trackColor = MaterialTheme.colorScheme.primary
            )

            Text(text = text, modifier = Modifier.padding(top = 16.dp), fontSize = fontSize)
        }
    }
}


@Composable
@Preview
fun PreviewThis(){
    weSee("Signing In", 16.sp
    )
}

