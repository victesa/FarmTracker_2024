package com.victorkirui.profile.ui.animalSelection

import android.app.Activity
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.victorkirui.ui.components.GreenButton



@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun AnimalSelectionRoute(context: Context){
    val windowWidthSizeClass = calculateWindowSizeClass(activity = context as Activity).widthSizeClass

    AnimalSelectionScreen(windowWidthSizeClass = windowWidthSizeClass)
}

@Composable
internal fun AnimalSelectionScreen(windowWidthSizeClass: WindowWidthSizeClass){
    when(windowWidthSizeClass){
        WindowWidthSizeClass.Compact ->{
            AnimalSelectionCompact( onClick = {})
        }

        WindowWidthSizeClass.Medium ->{
            AnimalSelectionMedium(onClick = {})
        }

    }

}


@Composable
internal fun AnimalSelectionCompact(viewModel: ProfileViewModel = hiltViewModel(),
                                    onClick:() -> Unit){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        .padding(horizontal = 16.dp)) {

        Column(modifier = Modifier
            .fillMaxHeight(.1f)
            .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center){
            Text(text = "Choose Animals you have", fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold, textAlign = TextAlign.Center)
        }
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Adaptive(minSize = 92.dp),
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.9f)
                .background(color = Color.White),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(viewModel.animalNamesList.size) {
                val animalName = viewModel.animalNamesList[it]

                ShimmerListItem(
                    animalUrl = viewModel.listOfAnimalsImageUrl[it],
                    animalName = animalName,
                    fontSize = 16.sp,
                    imageSize = 92.dp,
                    onClick = {viewModel.animalClicked(it)}
                )

            }
        }

        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
            verticalArrangement = Arrangement.Center){
            GreenButton(onClick = onClick, fontSize = 16.sp, text = "Next")
        }




    }
}

@Composable
internal fun AnimalSelectionMedium(onClick:() -> Unit,
                                   viewModel: ProfileViewModel = hiltViewModel()){
    val animalList = viewModel.uiState.collectAsState()
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {

        //Top Text Column
        Column(modifier = Modifier
            .fillMaxHeight(.1f)
            .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center){
            Text(text = "Choose Animals you have", fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold, textAlign = TextAlign.Center)
        }

        // Animal List Column/Grid
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Adaptive(minSize = 130.dp),
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.9f)
                .background(color = Color.White),
            verticalItemSpacing = 24.dp,
            contentPadding = PaddingValues(40.dp)
        ) {
            items(animalList.value.size, key = null) {
                val animalName = viewModel.animalNamesList[it]
                
                ShimmerListItem(
                    animalUrl = viewModel.listOfAnimalsImageUrl[it],
                    animalName = animalName,
                    fontSize = 17.sp,
                    imageSize = 132.dp,
                    onClick = {viewModel.animalClicked(it)}
                )
            }}

        Column(modifier = Modifier
            .fillMaxWidth(.7f)
            .fillMaxHeight(),
            verticalArrangement = Arrangement.Center){
            GreenButton(onClick = onClick, fontSize = 16.sp, text = "Next")
        }
    }

}


@Composable
@Preview(showBackground = true)
internal fun PreviewCompact(){
    //AnimalSelectionCompact()
}

@Composable
@Preview(showBackground = true, device = "spec:width=673dp,height=841dp")
internal fun PreviewMedium(){

}