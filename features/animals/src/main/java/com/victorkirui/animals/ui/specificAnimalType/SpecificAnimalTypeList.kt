package com.victorkirui.animals.ui.specificAnimalType

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.victorkirui.animals.R
import com.victorkirui.animals.ui.entities.AnimalState
import com.victorkirui.ui.getDrawable
import java.io.File

@Composable
fun SpecificAnimalTypeListRoute(viewModel: SpecificAnimalTypeListViewModel = hiltViewModel(),
                                         windowWidthSizeClass: WindowWidthSizeClass,
                                         currentAnimal: String,
                                         navigateToAddAnimals:(String) -> Unit){

    val  animalList = viewModel.getAnimalTypeFromDatabase(currentAnimal).collectAsState(initial = null)

    if (animalList.value == null){
        return
    }

    if (animalList.value!!.isEmpty()){
        Scaffold(
            floatingActionButton = { FloatingActionButton(onClick = {navigateToAddAnimals(currentAnimal)}) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }}
        ) {
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(it),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {
                Text(text = "No Data")
            }
        }

    }else{
        SpecificAnimalTypeListScreen(
            windowWidthSizeClass = windowWidthSizeClass,
            groupedAnimals = animalList.value!!.groupBy { it.name.first() }.toSortedMap(),
            onAddAnimalClicked = {
                                 navigateToAddAnimals(it)
            },
            currentAnimal = currentAnimal
        )

    }

}

@Composable
internal  fun SpecificAnimalTypeListScreen(windowWidthSizeClass: WindowWidthSizeClass,
                                   groupedAnimals: Map<Char, List<AnimalState>>,
                                           onAddAnimalClicked:(String) -> Unit,
                                           currentAnimal: String){
    when(windowWidthSizeClass){
        WindowWidthSizeClass.Compact ->{
            SpecificAnimalTypeListCompact(groupedAnimals = groupedAnimals,
                onAddAnimalClicked = {onAddAnimalClicked(it)},
                currentAnimal)
        }

        WindowWidthSizeClass.Medium ->{
            SpecificAnimalTypeListMedium(groupedAnimals = groupedAnimals,
                onAddAnimalClicked = {onAddAnimalClicked(it)},
                currentAnimal)
        }
    }
}


@Composable
fun SpecificAnimalTypeListCompact(groupedAnimals: Map<Char, List<AnimalState>>,
                                  onAddAnimalClicked:(String) -> Unit,
                                  currentAnimal: String){
    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = { onAddAnimalClicked(currentAnimal) }) {
        Icon(imageVector = Icons.Default.Add,
            contentDescription = null)

    }}) {paddingValues->
        Column(modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp)) {

            TopPart()

            LazyColumn(modifier = Modifier
                .padding(top = 16.dp)
                .padding(paddingValues)){
                groupedAnimals.forEach {(initial, animalState) ->

                    item {
                        ElevatedAnimalCard(animalState = animalState,
                            headlineFontSize = 17.sp,
                            supportingFontSize = 14.sp,
                            currentAnimal)
                        Spacer(modifier = Modifier.height(24.dp))
                    }
                }
            }
        }
    }

}



@Composable
fun SpecificAnimalTypeListMedium(groupedAnimals: Map<Char, List<AnimalState>>,
                                 onAddAnimalClicked:(String) -> Unit,
                                 currentAnimal: String){

    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = { onAddAnimalClicked(currentAnimal) }) {
            Icon(imageVector = Icons.Default.Add,
                contentDescription = null)

        }}) {paddingValues->
        Column(modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp)) {

            TopPart()

            LazyColumn(modifier = Modifier
                .padding(top = 24.dp)
                .padding(paddingValues)){
                groupedAnimals.forEach {(initial, animalState) ->

                    item {
                        ElevatedAnimalCard(animalState = animalState,
                            headlineFontSize = 18.sp,
                            supportingFontSize = 15.sp,
                            currentAnimal)

                        Spacer(modifier = Modifier.height(24.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun ElevatedAnimalCard(animalState: List<AnimalState>,
                       headlineFontSize: TextUnit,
                       supportingFontSize: TextUnit,
                       currentAnimal: String){
    val context = LocalContext.current

    ElevatedCard {
        animalState.forEach {
            ListItem(headlineContent = {
                Text(text = it.name, fontSize = headlineFontSize, color = Color.Black, fontWeight = FontWeight.Bold)
            },
                supportingContent = {
                    Text(text = "Age: ${it.age}", fontSize = supportingFontSize, color = Color.DarkGray)
                },
                leadingContent = {
                    Image(bitmap = getAnimalImage(it.animalImage,
                        currentAnimal,
                        context).asImageBitmap(), contentDescription = null, modifier = Modifier
                        .clip(CircleShape)
                        .size(48.dp))
                },
                trailingContent = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Outlined.Info, contentDescription = null, tint = MaterialTheme.colorScheme.secondary)
                    }
                },
                colors = ListItemColors(containerColor = Color.Transparent,
                    disabledHeadlineColor = Color.Transparent,
                    disabledLeadingIconColor = Color.Gray,
                    disabledTrailingIconColor = Color.Gray,
                    headlineColor = Color.Black,
                    leadingIconColor = Color.Transparent,
                    overlineColor = Color.Transparent,
                    supportingTextColor = Color.DarkGray,
                    trailingIconColor = Color.Green)
            )
            HorizontalDivider()
        }

    }
}

private fun getAnimalImage(
    fileName: String?,
    currentAnimal: String,
    context: Context
): Bitmap {
    return if (fileName != null && File(context.filesDir, fileName).exists()) {
        BitmapFactory.decodeFile(File(context.filesDir, fileName).absolutePath)
    } else {
        // Load a placeholder image from resources or create a default bitmap
        val defaultImageResId = getDrawable(currentAnimal)
        BitmapFactory.decodeResource(context.resources, defaultImageResId)
    }
}


@Composable
fun TopPart(){
    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(.25f)) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.35f)
            .padding(top = 24.dp),
            verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = Icons.AutoMirrored.Outlined.ArrowBack, contentDescription = null)
        }

        Column(modifier = Modifier.fillMaxWidth().fillMaxHeight().padding(vertical = 8.dp), verticalArrangement = Arrangement.SpaceBetween) {
            Text(text = "Animals", fontSize = 36.sp,
                fontWeight = FontWeight.Bold, color = Color.Black,
                style = MaterialTheme.typography.headlineLarge)

            SearchField()
        }
    }
}


@Composable
fun SearchField(){
    OutlinedTextField(value = "", onValueChange = {}, leadingIcon = { Icon(
        imageVector = Icons.Default.Search,
        contentDescription = null
    )},modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp), shape = RoundedCornerShape(30.dp))

}

@Composable
@Preview(showBackground = true)
fun PreviewThis(){
    Scaffold(floatingActionButton = { FloatingActionButton(onClick = { /*TODO*/ }) {
        Icon(imageVector = Icons.Default.Add, contentDescription = null)

    }}) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(it)
            .padding(horizontal = 16.dp)
            .background(Color.White)) {
            TopPart()
        }
    }
}

@Composable
@Preview(showBackground = true, device = "spec:width=673dp,height=841dp")
fun PreviewTablet(){
    Scaffold(floatingActionButton = { FloatingActionButton(onClick = { /*TODO*/ }) {
        Icon(imageVector = Icons.Default.Add, contentDescription = null)

    }}) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(it)
            .padding(horizontal = 24.dp)
            .background(Color.White)) {
            TopPart()
        }
    }
}