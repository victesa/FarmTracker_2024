package com.victorkirui.profile.ui.addAnimalData

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.victorkirui.profile.ui.addAnimalData.components.AnimalDataTextFields
import com.victorkirui.profile.ui.addAnimalData.components.WhiteButton
import com.victorkirui.ui.components.GreenButton
import org.w3c.dom.Text

@Composable
internal fun ImportAnimalInfoRoute(){

}

@Composable
internal fun ImportAnimalInfoScreen(){

}

@Composable
fun ImportAnimalInfoCompact(){
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
        .background(MaterialTheme.colorScheme.secondaryContainer)
        .padding(10.dp)) {

        TopTextsColumn(headlineFontSize = 24.sp, bodyFontSize = 16.sp)

        AnimalInfoTextFields()

        AnimalInfoOptionsButton(fontSize = 16.sp)

    }

}

@Composable
fun ImportAnimalInfoMedium(){
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(24.dp)
        .background(MaterialTheme.colorScheme.secondaryContainer),
        horizontalAlignment = Alignment.CenterHorizontally) {

        Column(modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(.7f)) {

            TopTextsColumn(headlineFontSize = 28.sp, bodyFontSize = 17.sp)

            AnimalInfoTextFields()

            AnimalInfoOptionsButton(fontSize = 17.sp)
        }

    }

}

@Composable
fun TopTextsColumn(headlineFontSize: TextUnit, bodyFontSize: TextUnit){
    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(.25f),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Import Data",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.ExtraBold,
            fontSize = headlineFontSize,
            color = Color.Black
        )

        Text(text = "There are information we allow you to record on this app in this version. Enter the name of the column that corresponds to the following data. If there isn’t any then you can leave it blank.",
            fontSize = bodyFontSize, textAlign = TextAlign.Center, color = Color.Black)

    }
}

@Composable
fun AnimalInfoTextFields(){
    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(.7f),
        verticalArrangement = Arrangement.Center) {
       AnimalDataTextFields(value = "", onValueChange = {}, placeholder = "Name")

        Spacer(modifier = Modifier.height(16.dp))

       AnimalDataTextFields(value = "", onValueChange = {}, placeholder = "Type")

        Spacer(modifier = Modifier.height(16.dp))

        AnimalDataTextFields(value = "", onValueChange = {}, placeholder = "Age")

        Spacer(modifier = Modifier.height(16.dp))

        AnimalDataTextFields(value = "", onValueChange = {}, placeholder = "Breed")

        Spacer(modifier = Modifier.height(16.dp))

        AnimalDataTextFields(value = "", onValueChange = {}, placeholder = "Gender")
    }
}

@Composable
fun AnimalInfoOptionsButton(fontSize: TextUnit){
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center) {
        GreenButton(onClick = { /*TODO*/ }, fontSize = fontSize, text = "ImportData")
        Spacer(modifier = Modifier.height(16.dp))
        WhiteButton(onClick = { /*TODO*/ }, text = "Skip", fontSize = fontSize)
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewCompact(){
    Column(modifier = Modifier.fillMaxSize()) {
        ImportAnimalInfoCompact()
    }
}

@Composable
@Preview(showBackground = true, device = "spec:width=673dp,height=841dp")
fun PreviewMedium(){
    Column(modifier = Modifier.fillMaxSize()) {
        ImportAnimalInfoMedium()
    }
}
