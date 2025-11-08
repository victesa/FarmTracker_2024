package com.victorkirui.home.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.victorkirui.home.ui.AnimalState
import com.victorkirui.ui.components.AnimalsBriefButton

@Composable
fun AnimalBriefSection(modifier: Modifier,
                       fontSize: TextUnit,
                       numberOfColumns: Int,
                       animalList: List<AnimalState>,
                       navigateToSpecificAnimal:(String) -> Unit,
                       navigateToAAllTypesOfAnimalsList:() -> Unit){
    Column(modifier = modifier) {
        Text(text = "Animals", fontSize = fontSize, color = Color.Black, modifier = Modifier.padding(16.dp))
        LazyVerticalGrid(columns = GridCells.Fixed(numberOfColumns),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ){
            items(animalList.size){
                AnimalsBriefButton(
                    painter = animalList[it].icon,
                    animalName = animalList[it].name,
                    fontSize = fontSize,
                    numberOfAnimal = animalList[it].number.toString(),
                    onClick = {navigateToSpecificAnimal(animalList[it].name)})
            }
        }
        TextButton(onClick = {
            navigateToAAllTypesOfAnimalsList()
        }) {
            Text(text = "See All", fontSize = fontSize, color = Color.Gray)
        }
    }
}


@Composable
@Preview
fun PreviewAnimalBrief(){

}