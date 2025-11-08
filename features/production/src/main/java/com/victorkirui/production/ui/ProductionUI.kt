package com.victorkirui.production.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.victorkirui.designsystem.theme.AppTheme
import com.victorkirui.production.components.AddButton
import com.victorkirui.production.components.GoalIndicatorRow
import com.victorkirui.production.components.HistoryLayout
import com.victorkirui.production.components.ScreenTitle
import com.victorkirui.production.domain.ProductionUiState

@Composable
fun ProductionUIRoute(windowWidthSizeClass: WindowWidthSizeClass,
                      viewModel: ProductionUiViewModel = hiltViewModel()){

    val uiState = viewModel.productionSubList.collectAsState()

    ProductionUIScreen(windowWidthSizeClass = windowWidthSizeClass, productionList = uiState.value)
}

@Composable
internal fun ProductionUIScreen(windowWidthSizeClass: WindowWidthSizeClass,
                                productionList: List<ProductionUiState>){
    when(windowWidthSizeClass){
        WindowWidthSizeClass.Compact -> {
            ProductionUICompact(productionList = productionList)
        }

        WindowWidthSizeClass.Medium ->{
            ProductionUIMedium(productionList = productionList)
        }
    }
}

@Composable
internal fun ProductionUICompact(productionList: List<ProductionUiState>){
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp)) {

        ScreenTitle(fontSize = 28.sp, heightPercentage = .1f)

        GoalIndicatorRow(progress = .2f, completedGoal = 20, fontSize = 16.sp,
            heightPercentage = .1f,
            size = 85.dp,
            strokeWidth = 10.dp)

        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.2f),
            verticalArrangement = Arrangement.Center) {
            AddButton(fontSize = 16.sp, paddingText = 8.dp)
        }

        HistoryLayout(
            productionList = productionList,
            heightPercentage = .5f,
            fontSize = 17.sp
        )


    }


}

@Composable
internal fun ProductionUIMedium(productionList: List<ProductionUiState>){
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {

        ScreenTitle(fontSize = 34.sp, heightPercentage = .1f)

        GoalIndicatorRow(
            progress = .2f,
            completedGoal = 20,
            fontSize = 20.sp,
            heightPercentage = .2f,
            size = 140.dp,
            strokeWidth = 15.dp
        )

        Column(modifier = Modifier
            .fillMaxWidth(.7f)
            .fillMaxHeight(.2f),
            verticalArrangement = Arrangement.Center) {
            AddButton(fontSize = 17.sp, paddingText = 8.dp)
        }

        Column(modifier = Modifier
            .fillMaxWidth(.7f)
            .padding(top = 16.dp)) {
            HistoryLayout(
                productionList = productionList,
                heightPercentage = .6f,
                fontSize = 18.sp
            )
        }

    }

}

@Composable
@Preview(showBackground = true)
internal fun ProductionUICompactPreview(){
    AppTheme {
        val list = listOf<ProductionUiState>()
        ProductionUICompact(list)
    }
}

@Composable
@Preview(showBackground = true, device = "spec:width=673dp,height=841dp")
internal fun ProductionUIMediumPreview(){
    AppTheme {
        val list = listOf<ProductionUiState>()
        ProductionUIMedium(list)
    }

}