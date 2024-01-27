package com.victorkirui.profile.ui.addAnimalData

import androidx.compose.foundation.Image
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
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.victorkirui.profile.R
import com.victorkirui.profile.ui.addAnimalData.components.WhiteButton
import com.victorkirui.ui.components.GreenButton

@Composable
internal fun AddAnimalDataOptionRoute(windowWidthSizeClass: WindowWidthSizeClass){

}

@Composable
internal fun AddAnimalDataOptionScreen(windowWidthSizeClass: WindowWidthSizeClass){
    when(windowWidthSizeClass){
        WindowWidthSizeClass.Compact ->{
            AddAnimalDataOptionCompact()
        }

        WindowWidthSizeClass.Medium ->{
            AddAnimalDataOptionMedium()
        }
    }

}

@Composable
fun AddAnimalDataOptionCompact(){
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 24.dp)
        .background(MaterialTheme.colorScheme.secondaryContainer)
        .padding(10.dp)) {

        TopTextColumn(headerFontSize = 24.sp, bodyFontSize = 16.sp)

        ExportIllustrator()

        AddAnimalButtonOptions(fontSize = 16.sp)

    }
}

@Composable
fun AddAnimalDataOptionMedium(){
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 24.dp)
        .background(MaterialTheme.colorScheme.secondaryContainer),
        horizontalAlignment = Alignment.CenterHorizontally) {

        Column(modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(.7f)) {
            TopTextColumn(headerFontSize = 28.sp, bodyFontSize = 17.sp)

            ExportIllustrator()

            AddAnimalButtonOptions(fontSize = 17.sp)
        }

    }
}

@Composable
fun TopTextColumn(headerFontSize: TextUnit, bodyFontSize: TextUnit){
    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(.2f),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Text(text = "Add Animal Data", style = MaterialTheme.typography.headlineLarge, fontSize = headerFontSize, fontWeight = FontWeight.ExtraBold, color = Color.Black)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "We have made it easier for you to integrate this software in your farm. You can now import and synchronize your data from a spreadsheet",
            color = Color.Black, fontSize = bodyFontSize,
            textAlign = TextAlign.Center)
    }
}

@Composable
fun ExportIllustrator(){
    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(.6f)) {
        Image(painter = painterResource(id = R.drawable.export_illustration),
            contentDescription = "Export illustration",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit)
    }
}

@Composable
fun AddAnimalButtonOptions(fontSize: TextUnit){
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center) {
        GreenButton(onClick = { /*TODO*/ }, fontSize = fontSize, text = "ImportData")
        Spacer(modifier = Modifier.height(16.dp))
        WhiteButton(onClick = { /*TODO*/ }, text = "Add Data Manually", fontSize = fontSize)
    }
}

@Composable
@Preview(showBackground = true)
fun Preview(){
    Column(modifier = Modifier
        .background(Color.White)
        .fillMaxSize()) {

    }
    AddAnimalDataOptionCompact()
}

@Composable
@Preview(showBackground = true, device = "spec:width=673dp,height=841dp")
fun Preview2(){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)) {
        AddAnimalDataOptionMedium()
    }
}