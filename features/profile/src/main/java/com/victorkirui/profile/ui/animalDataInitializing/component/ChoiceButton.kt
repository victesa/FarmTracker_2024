package com.victorkirui.profile.ui.animalDataInitializing.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun ChoiceButton(text: String, fontSize: TextUnit, checked: Boolean, onCheckChange:() -> Unit){
    FilledTonalButton(onClick = { /*TODO*/ },
        modifier = Modifier.fillMaxWidth()) {
        Text(text = text,
            fontSize = fontSize, color = Color.Black, modifier = Modifier
                .fillMaxWidth(.85f))

        Checkbox(checked = checked,
            onCheckedChange = { onCheckChange() },
            modifier = Modifier.background(shape = CircleShape, color = MaterialTheme.colorScheme.secondaryContainer))
    }
}


@Composable
fun RoundCheckBox(){
    IconButton(onClick = { /*TODO*/ }) {
        Icon(imageVector = Icons.Default.CheckCircle,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.secondaryContainer,
            modifier = Modifier.background(shape = CircleShape, color = MaterialTheme.colorScheme.secondaryContainer))
    }
}

@Composable
@Preview(showBackground = true)
fun Preview(){
    RoundCheckBox()
}