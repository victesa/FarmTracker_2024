package com.victorkirui.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.victorkirui.home.ui.components.AnimalBriefSection
import com.victorkirui.home.ui.components.DurationRow
import com.victorkirui.home.ui.components.FinanceSummary

@Composable
fun HomeRoute(windowWidthSizeClass: WindowWidthSizeClass,
              viewModel: HomeViewModel = hiltViewModel(),
              navigateToSpecificAnimalScreen:(String) -> Unit,
              navigateToAllTypeOfAnimalsList: () -> Unit){
    val showEmptyMessage = viewModel.financeState.collectAsState().value.showFinanceEmptyMessage
    val animalList = viewModel.animalList.collectAsState().value

    val smallAnimalList = if (animalList.size > 3){animalList.subList(0,3)}else{animalList}

    HomeScreen(windowWidthSizeClass = windowWidthSizeClass,
        showEmptyMessage = showEmptyMessage,
        smallAnimalList,
        navigateToSpecificAnimalScreen,
        navigateToAllTypeOfAnimalsList)
}

@Composable
internal fun HomeScreen(windowWidthSizeClass: WindowWidthSizeClass,
                        showEmptyMessage: Boolean,
                        animalList: List<AnimalState>,
                        navigateToSpecificAnimalScreen: (String) -> Unit,
                        navigateToAllTypeOfAnimalsList: () -> Unit){
    when(windowWidthSizeClass){
        WindowWidthSizeClass.Compact ->{
            HomeCompact(showEmptyMessage,
                animalList,
                navigateToSpecificAnimalScreen,
                navigateToAllTypeOfAnimalsList)
        }

        WindowWidthSizeClass.Medium ->{
            HomeMedium(showEmptyMessage,
                animalList,
                navigateToSpecificAnimalScreen,
                navigateToAllTypeOfAnimalsList)
        }
    }
}

@Composable
fun HomeCompact(showEmptyMessage: Boolean,
                animalList: List<AnimalState>,
                navigateToSpecificAnimalScreen: (String) -> Unit,
                navigateToAllTypeOfAnimalsList:() -> Unit){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally) {
        TopBar()

        DurationRow(fontSize = 16.sp)

        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.4f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            FinanceSummary(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.8f)
                    .padding(horizontal = 28.dp)
                    .border(shape = RoundedCornerShape(15.dp), color = Color.Gray, width = 1.dp),
                showEmptyMessage = showEmptyMessage,
                16.sp
            )
        }

        AnimalBriefSection(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(), fontSize = 16.sp, 2,
            animalList = animalList,
            navigateToSpecificAnimal = navigateToSpecificAnimalScreen,
            navigateToAAllTypesOfAnimalsList = {navigateToAllTypeOfAnimalsList()})

    }

}

@Composable
fun TopBar(){
    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(.1f)
        .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center){
        Text(text = "Home", fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.headlineLarge,
            color = Color.Black
        )
    }
}

@Composable
fun HomeMedium(showEmptyMessage: Boolean,
               animalList: List<AnimalState>,
               navigateToSpecificAnimalScreen: (String) -> Unit,
               navigateToAllTypeOfAnimalsList: () -> Unit){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally) {
        TopBar()

        DurationRow(fontSize = 17.sp)

        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.4f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            FinanceSummary(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.8f)
                    .padding(horizontal = 28.dp)
                    .border(shape = RoundedCornerShape(15.dp), color = Color.Gray, width = 1.dp),
                showEmptyMessage = showEmptyMessage,
                17.sp
            )
        }

        AnimalBriefSection(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(), fontSize = 17.sp,3, animalList = animalList,
            navigateToSpecificAnimal = navigateToSpecificAnimalScreen,
            navigateToAAllTypesOfAnimalsList = {navigateToAllTypeOfAnimalsList()})

    }
}


@Composable
@Preview(showBackground = true)
fun PreviewHome(){
}
