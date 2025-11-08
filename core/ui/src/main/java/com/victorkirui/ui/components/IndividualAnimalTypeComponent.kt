package com.victorkirui.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

@Composable
fun AnimalsBriefButton(painter: Int,
                       animalName: String,
                       fontSize: TextUnit,
                       numberOfAnimal: String,
                       onClick:() -> Unit){
    Column(
        modifier = Modifier
            .background(color = Color(0xFFF7F7F7), shape = RoundedCornerShape(10.dp))
            .fillMaxWidth(.4f)
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.45f),
            horizontalArrangement = Arrangement.SpaceBetween) {
            Icon(painter = painterResource(id = painter),
                contentDescription = null,
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape)
                    .background(Color.White, shape = CircleShape),
                tint = Color.Black)

            Icon(imageVector = Icons.Default.Check, contentDescription = null,
                tint = MaterialTheme.colorScheme.secondary)
        }

        Row(modifier = Modifier
            .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {
            Column() {
                Text(text = animalName,
                    fontSize = fontSize,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold)

                Text(text = numberOfAnimal,
                    fontSize = fontSize,
                    color = Color.DarkGray)
            }

            Icon(imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight, contentDescription = null,
                modifier = Modifier.scale(2f))

        }
    }
}