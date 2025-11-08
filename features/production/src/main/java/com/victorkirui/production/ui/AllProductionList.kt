package com.victorkirui.production.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import com.victorkirui.designsystem.theme.AppTheme
import com.victorkirui.production.domain.ProductionUiState

@Composable
fun AllProductionListRoute(viewModel: ProductionUiViewModel = hiltViewModel(),
                           windowWidthSizeClass: WindowWidthSizeClass,
                           navigateToAddProductionUi:() -> Unit){

    val uiState = viewModel.productionList.collectAsState()

    AllProductionListScreen(windowWidthSizeClass = windowWidthSizeClass,
        productionUiList = uiState.value,
        addProduct = {navigateToAddProductionUi()})

}

@Composable
internal fun AllProductionListScreen(windowWidthSizeClass: WindowWidthSizeClass,
                                     productionUiList: List<ProductionUiState>,
                                     addProduct: () -> Unit){

    when(windowWidthSizeClass){
        WindowWidthSizeClass.Compact ->{
            AllProductionListCompact(productionUiList = productionUiList,
                addProduct = {addProduct()})
        }

        WindowWidthSizeClass.Medium ->{
            AllProductionListMedium(productionUiList = productionUiList,
                addProduct = {addProduct()})
        }
    }

}

@Composable
internal fun AllProductionListCompact(productionUiList: List<ProductionUiState>,
                                      addProduct:() -> Unit){
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { addProduct() }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(it)
            .padding(horizontal = 16.dp)) {

            TopPart(1f)

            LazyColumn(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()){
                items(productionUiList.size){
                    ElevatedCard(modifier = Modifier.padding(top = 8.dp)) {
                        ListItem(headlineContent = {
                            Text(text = productionUiList[it].productName)
                        },
                            supportingContent = {
                                Text(text = productionUiList[it].productDate)
                            },
                            trailingContent = {
                                Text(text = productionUiList[it].productQuantity)
                            })
                    }
                    
                }
            }

        }
    }

}

@Composable
internal fun AllProductionListMedium(productionUiList: List<ProductionUiState>,
                                     addProduct: () -> Unit){
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { addProduct() }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(it)
            .padding(horizontal = 24.dp)) {

            TopPart(.8f)

            LazyColumn(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally){
                items(productionUiList.size){
                    ElevatedCard(modifier = Modifier.padding(top = 8.dp)) {
                        ListItem(headlineContent = {
                            Text(text = productionUiList[it].productName)
                        },
                            supportingContent = {
                                Text(text = productionUiList[it].productDate)
                            },
                            trailingContent = {
                                Text(text = productionUiList[it].productQuantity)
                            },
                            modifier = Modifier.fillMaxWidth(.8f))
                    }

                }
            }

        }
    }

}

@Composable
fun SearchField(widthPercentage: Float){
    OutlinedTextField(value = "", onValueChange = {}, leadingIcon = { Icon(
        imageVector = Icons.Default.Search,
        contentDescription = null
    )
    },modifier = Modifier
        .fillMaxWidth(widthPercentage)
        .padding(8.dp), shape = RoundedCornerShape(30.dp)
    )

}

@Composable
fun TopPart(widthPercentage: Float){
    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(.25f),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.35f)
            .padding(top = 24.dp),
            verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = Icons.AutoMirrored.Outlined.ArrowBack, contentDescription = null)
        }

        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(vertical = 8.dp), verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Animals", fontSize = 36.sp,
                fontWeight = FontWeight.Bold, color = Color.Black,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.fillMaxWidth())

            SearchField(widthPercentage)
        }
    }
}



@Composable
@Preview(showBackground = true)
internal fun AllProductionListCompactPreview(){
    AppTheme {
        val list = listOf<ProductionUiState>(
            ProductionUiState(
                productName = "Milk",
                productQuantity = "20kgs",
                productDate = "20/04/2020"
            ),
            ProductionUiState(
                productName = "Milk",
                productQuantity = "20kgs",
                productDate = "20/04/2020"
            ),
            ProductionUiState(
                productName = "Milk",
                productQuantity = "20kgs",
                productDate = "20/04/2020"
            ),
            ProductionUiState(
                productName = "Milk",
                productQuantity = "20kgs",
                productDate = "20/04/2020"
            ),
            ProductionUiState(
                productName = "Milk",
                productQuantity = "20kgs",
                productDate = "20/04/2020"
            )
        )
        AllProductionListCompact(productionUiList = list){}
    }
}

@Composable
@Preview(showBackground = true, device = "spec:width=673dp,height=841dp")
internal fun AllProductionListMediumPreview(){
    AppTheme {
        val list = listOf<ProductionUiState>(
            ProductionUiState(
                productName = "Milk",
                productQuantity = "20kgs",
                productDate = "20/04/2020"
            ),
            ProductionUiState(
                productName = "Milk",
                productQuantity = "20kgs",
                productDate = "20/04/2020"
            ),
            ProductionUiState(
                productName = "Milk",
                productQuantity = "20kgs",
                productDate = "20/04/2020"
            ),
            ProductionUiState(
                productName = "Milk",
                productQuantity = "20kgs",
                productDate = "20/04/2020"
            ),
            ProductionUiState(
                productName = "Milk",
                productQuantity = "20kgs",
                productDate = "20/04/2020"
            )
        )
        AllProductionListMedium(productionUiList = list){}
    }
}