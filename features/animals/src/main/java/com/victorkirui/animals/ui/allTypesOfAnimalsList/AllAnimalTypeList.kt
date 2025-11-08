package com.victorkirui.animals.ui.allTypesOfAnimalsList

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.victorkirui.animals.ui.AnimalTypesBasicInfo
import com.victorkirui.ui.components.AnimalsBriefButton

@Composable
fun AllAnimalTypeListRoute(viewModel: AllAnimalTypesListViewModel = hiltViewModel(),
                           windowWidthSizeClass: WindowWidthSizeClass,
                           navigateToSpecificAnimal:(String) -> Unit){

    val animalListState = viewModel.animalBasicInfo.collectAsState()

    Log.d("animalList", animalListState.value.toString())

    AllAnimalTypeListScreen(windowWidthSizeClass = windowWidthSizeClass,
        animalList = animalListState.value,
        onClick = {navigateToSpecificAnimal(it)})
}

@Composable
internal fun AllAnimalTypeListScreen(windowWidthSizeClass: WindowWidthSizeClass,
                                     animalList: List<AnimalTypesBasicInfo>,
                                     onClick: (String) -> Unit){
    when(windowWidthSizeClass){
        WindowWidthSizeClass.Compact ->{
            AllAnimalTypeListCompact(animalList = animalList,
                onClick = { onClick(it) })
        }

        WindowWidthSizeClass.Medium ->{
            AllAnimalTypeListMedium(animalList = animalList,
                onClick = { onClick(it) })
        }
    }
}

@Composable
fun AllAnimalTypeListCompact(animalList: List<AnimalTypesBasicInfo>,
                             onClick:(String) -> Unit){
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
    ) {

        LazyVerticalStaggeredGrid(columns = StaggeredGridCells.Fixed(2),
            modifier = Modifier
                .padding(it)
                .padding(horizontal = 16.dp)
                .padding(top = 24.dp),
            contentPadding = PaddingValues(8.dp)
        ){

            items(animalList.size){animal->
                AnimalsBriefButton(painter = animalList[animal].icon,
                    animalName = animalList[animal].name,
                    fontSize = 16.sp,
                    numberOfAnimal = animalList[animal].number.toString(),
                    onClick = {
                        onClick(animalList[animal].name)
                    })
            }

        }
    }
}

@Composable
fun AllAnimalTypeListMedium(animalList: List<AnimalTypesBasicInfo>,
                            onClick: (String) -> Unit){
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
    ) {

        LazyVerticalStaggeredGrid(columns = StaggeredGridCells.Fixed(2),
            modifier = Modifier
                .padding(it)
                .padding(horizontal = 24.dp)){

            items(animalList.size){animal->
                AnimalsBriefButton(painter = animalList[animal].icon,
                    animalName = animalList[animal].name,
                    fontSize = 16.sp,
                    numberOfAnimal = animalList[animal].number.toString(),
                    onClick = {
                        onClick(animalList[animal].name)
                    })
            }

        }
    }
}