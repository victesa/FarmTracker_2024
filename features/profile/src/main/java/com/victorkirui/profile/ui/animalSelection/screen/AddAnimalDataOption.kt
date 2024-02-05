package com.victorkirui.profile.ui.animalSelection.screen

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.victorkirui.profile.R
import com.victorkirui.profile.ui.animalSelection.AddAnimalDataViewModel
import com.victorkirui.profile.ui.animalSelection.component.WhiteButton
import com.victorkirui.ui.components.GreenButton

@Composable
internal fun AddAnimalDataOptionRoute(windowWidthSizeClass: WindowWidthSizeClass,
                                      onNavigateToImportAnimalInfoScreen: () -> Unit){
    AddAnimalDataOptionScreen(windowWidthSizeClass = windowWidthSizeClass,
        onNavigateToImportAnimalInfoScreen = onNavigateToImportAnimalInfoScreen)

}

@Composable
internal fun AddAnimalDataOptionScreen(windowWidthSizeClass: WindowWidthSizeClass,
                                       onNavigateToImportAnimalInfoScreen: () -> Unit){
    when(windowWidthSizeClass){
        WindowWidthSizeClass.Compact ->{
            AddAnimalDataOptionCompact(onNavigateToImportAnimalInfoScreen = onNavigateToImportAnimalInfoScreen)
        }

        WindowWidthSizeClass.Medium ->{
            AddAnimalDataOptionMedium(onNavigateToImportAnimalInfoScreen = onNavigateToImportAnimalInfoScreen)
        }
    }

}

@Composable
fun AddAnimalDataOptionCompact(onNavigateToImportAnimalInfoScreen: () -> Unit){
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 32.dp)
        .padding(bottom = 16.dp)
        .background(
            MaterialTheme.colorScheme.secondaryContainer,
            shape = RoundedCornerShape(32.dp)
        )
        .padding(10.dp)) {

        TopTextColumn(headerFontSize = 24.sp, bodyFontSize = 16.sp)

        ExportIllustrator()

        AddAnimalButtonOptions(fontSize = 16.sp, onNavigateToImportAnimalInfoScreen = onNavigateToImportAnimalInfoScreen)

    }
}

@Composable
fun AddAnimalDataOptionMedium(onNavigateToImportAnimalInfoScreen: () -> Unit){
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 32.dp)
        .padding(bottom = 16.dp)
        .background(
            MaterialTheme.colorScheme.secondaryContainer,
            shape = RoundedCornerShape(32.dp)
        ),
        horizontalAlignment = Alignment.CenterHorizontally) {

        Column(modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(.7f)) {
            TopTextColumn(headerFontSize = 28.sp, bodyFontSize = 17.sp)

            ExportIllustrator()

            AddAnimalButtonOptions(fontSize = 17.sp, onNavigateToImportAnimalInfoScreen = onNavigateToImportAnimalInfoScreen)
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
fun AddAnimalButtonOptions(fontSize: TextUnit,
                           viewModel: AddAnimalDataViewModel = hiltViewModel(),
                           onNavigateToImportAnimalInfoScreen: () -> Unit){
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = {
            viewModel.updateURI(it!!)
            Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show()
        })

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center) {
        GreenButton(onClick = {
            launcher.launch("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
            onNavigateToImportAnimalInfoScreen()
        }, fontSize = fontSize, text = "ImportData")
        Spacer(modifier = Modifier.height(16.dp))
        WhiteButton(onClick = {  }, text = "Add Data Manually", fontSize = fontSize)
    }
}

@Composable
@Preview(showBackground = true)
fun Preview(){
    Column(modifier = Modifier
        .background(Color.White)
        .fillMaxSize()) {

    }
    AddAnimalDataOptionCompact(){}
}

@Composable
@Preview(showBackground = true, device = "spec:width=673dp,height=841dp")
fun Preview2(){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)) {
        AddAnimalDataOptionMedium(){}
    }
}