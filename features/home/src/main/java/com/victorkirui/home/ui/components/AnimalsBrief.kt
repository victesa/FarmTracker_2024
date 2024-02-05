package com.victorkirui.home.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.victorkirui.home.R

@Composable
fun AnimalsBriefButton(painter: Int,
                       animalName: String,
                       fontSize: TextUnit,
                       numberOfAnimal: String){
    Column(
        modifier = Modifier
            .background(color = Color(0xFFF7F7F7), shape = RoundedCornerShape(10.dp))
            .fillMaxWidth(.4f)
            .padding(8.dp)
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.45f),
            horizontalArrangement = Arrangement.SpaceBetween) {
            Icon(painter = painterResource(id = painter),
                contentDescription = null,
                modifier = Modifier
                    .background(Color.White, shape = CircleShape))

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

            Icon(imageVector = Icons.Outlined.KeyboardArrowRight, contentDescription = null,
                modifier = Modifier.scale(2f))

        }
    }
}

@Composable
fun AnimalBriefSection(modifier: Modifier, fontSize: TextUnit){
    Column(modifier = modifier) {
        Text(text = "Animals", fontSize = fontSize, color = Color.Black)
        LazyVerticalGrid(columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ){
            items(4){
                AnimalsBriefButton(painter = R.drawable.cow,
                    animalName = "Cow", fontSize = 16.sp, numberOfAnimal = "20")
            }
        }
        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "See All", fontSize = fontSize, color = Color.Gray)
        }
    }
}


@Composable
@Preview
fun PreviewAnimalBrief(){

}