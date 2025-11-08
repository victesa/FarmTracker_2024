package com.victorkirui.addinfo.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.victorkirui.addinfo.R
import com.victorkirui.addinfo.ui.components.ImageButton
import com.victorkirui.designsystem.theme.AppTheme

@Composable
fun AddInfoUiRoute(navigateToAddAnimalScreen:() -> Unit,
                   navigateToAddExpensesScreen:() -> Unit,
                   navigateToAddProfitScreen:() -> Unit,
                   navigateToAddProductionScreen:() -> Unit,
                   windowWidthSizeClass: WindowWidthSizeClass){
    
    AddInfoUiScreen(
        navigateToAddAnimalScreen = navigateToAddAnimalScreen,
        navigateToAddExpensesScreen = navigateToAddExpensesScreen,
        navigateToAddProfitScreen = navigateToAddProfitScreen,
        navigateToAddProductionScreen = navigateToAddProductionScreen,
        windowWidthSizeClass = windowWidthSizeClass
    )



}

@Composable
internal fun AddInfoUiScreen(navigateToAddAnimalScreen:() -> Unit,
                             navigateToAddExpensesScreen:() -> Unit,
                             navigateToAddProfitScreen:() -> Unit,
                             navigateToAddProductionScreen:() -> Unit,
                             windowWidthSizeClass: WindowWidthSizeClass){

    when(windowWidthSizeClass){
        WindowWidthSizeClass.Compact ->{
            AddInfoUiCompact(
                navigateToAddAnimalScreen = { navigateToAddAnimalScreen() },
                navigateToAddExpensesScreen = { navigateToAddExpensesScreen() },
                navigateToAddProfitScreen = { navigateToAddProfitScreen() },
                navigateToAddProductionScreen = {navigateToAddProductionScreen()})
        }

        WindowWidthSizeClass.Medium ->{
            AddInfoUiMedium(
                navigateToAddAnimalScreen = { navigateToAddAnimalScreen() },
                navigateToAddExpensesScreen = { navigateToAddExpensesScreen() },
                navigateToAddProfitScreen = { navigateToAddProfitScreen() },
                navigateToAddProductionScreen = {navigateToAddProductionScreen()})
        }
    }

}

@Composable
internal fun AddInfoUiCompact(navigateToAddAnimalScreen:() -> Unit,
                              navigateToAddExpensesScreen:() -> Unit,
                              navigateToAddProfitScreen:() -> Unit,
                              navigateToAddProductionScreen:() -> Unit){
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp)) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.1f),
            verticalArrangement = Arrangement.Center) {
            Text(text = "Add", fontSize = 36.sp,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold)

        }

        LazyColumn(){
            item {
                ImageButton(image = R.drawable.animal_pic,
                    text = "Animals",
                    fontSize = 20.sp,
                    onClick = {navigateToAddAnimalScreen()},
                    widthPercentage = 1f)

                Spacer(modifier = Modifier.height(16.dp))
            }


            item {
                ImageButton(image = R.drawable.expenses_pic,
                    text = "Expenses",
                    fontSize = 20.sp,
                    onClick = {navigateToAddExpensesScreen()},
                    widthPercentage = 1f)

                Spacer(modifier = Modifier.height(16.dp))
            }


            item {
                ImageButton(image = R.drawable.production_pic,
                    text = "Production",
                    fontSize = 20.sp,
                    onClick = {navigateToAddProductionScreen()},
                    widthPercentage = 1f)

                Spacer(modifier = Modifier.height(16.dp))
            }


            item {
                ImageButton(image = R.drawable.profit_pic,
                    text = "Income",
                    fontSize = 20.sp,
                    onClick = {navigateToAddProfitScreen()},
                    widthPercentage = 1f)

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }

}

@Composable
internal fun AddInfoUiMedium(navigateToAddAnimalScreen:() -> Unit,
                             navigateToAddExpensesScreen:() -> Unit,
                             navigateToAddProfitScreen:() -> Unit,
                             navigateToAddProductionScreen:() -> Unit){
    Column(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth(.6f)
        .padding(horizontal = 16.dp)) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.1f),
            verticalArrangement = Arrangement.Center) {
            Text(text = "Add", fontSize = 36.sp,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold)

        }

        LazyColumn(){
            item {
                ImageButton(image = R.drawable.animal_pic,
                    text = "Animals",
                    fontSize = 20.sp,
                    onClick = {navigateToAddAnimalScreen()},
                    widthPercentage = 1f)

                Spacer(modifier = Modifier.height(24.dp))
            }


            item {
                ImageButton(image = R.drawable.animal_pic,
                    text = "Expenses",
                    fontSize = 20.sp,
                    onClick = {navigateToAddExpensesScreen()},
                    widthPercentage = 1f)

                Spacer(modifier = Modifier.height(24.dp))
            }


            item {
                ImageButton(image = R.drawable.animal_pic,
                    text = "Production",
                    fontSize = 20.sp,
                    onClick = {navigateToAddProductionScreen()},
                    widthPercentage = 1f)

                Spacer(modifier = Modifier.height(24.dp))
            }


            item {
                ImageButton(image = R.drawable.animal_pic,
                    text = "Income",
                    fontSize = 20.sp,
                    onClick = {navigateToAddProfitScreen()},
                    widthPercentage = 1f)

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }

}

@Composable
@Preview(showBackground = true)
internal fun AddInfoUiCompactPreview(){
    AppTheme {
        AddInfoUiCompact(
            navigateToAddAnimalScreen = { /*TODO*/ },
            navigateToAddExpensesScreen = { /*TODO*/ },
            navigateToAddProfitScreen = { /*TODO*/ }) {
            
        }
    }
}

@Composable
@Preview(showBackground = true, device = "spec:width=673dp,height=841dp")
internal fun AddInfoUiMediumPreview(){
    AppTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            AddInfoUiMedium(
                navigateToAddAnimalScreen = { /*TODO*/ },
                navigateToAddExpensesScreen = { /*TODO*/ },
                navigateToAddProfitScreen = { /*TODO*/ }) {

            }
        }
    }
}