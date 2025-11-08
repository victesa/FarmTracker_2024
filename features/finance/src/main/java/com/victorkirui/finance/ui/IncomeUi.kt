package com.victorkirui.finance.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.victorkirui.designsystem.theme.AppTheme
import com.victorkirui.finance.domain.DataTypeForTopPart
import com.victorkirui.finance.domain.FinanceState
import com.victorkirui.finance.ui.components.HistoryLayout
import com.victorkirui.finance.ui.components.TopPartSummaryLayout

@Composable
fun IncomeUiRoute(windowWidthSizeClass: WindowWidthSizeClass,
                  viewModel: FinanceViewModel = hiltViewModel(),
                  navigateToAllListScreen: (String) -> Unit,
                  navigateToAddIncomeScreen: (String) -> Unit){

    val incomeState = viewModel.incomeState.collectAsState()

    val incomeList by remember(incomeState) {
        mutableStateOf(if (incomeState.value.size > 4){incomeState.value.subList(0,3)}else{incomeState.value})
    }

    val summaryState = viewModel.incomeSummaryState.collectAsState()

    IncomeUiScreen(windowWidthSizeClass = windowWidthSizeClass,
        incomeList = incomeList,
        navigateToAddIncomeScreen = {navigateToAddIncomeScreen(it)},
        navigateToAllListScreen = {navigateToAllListScreen(it)},
        currentContent = viewModel.currentContentState.value,
        dailyAmount = summaryState.value.today,
        weeklyAmount = summaryState.value.thisWeek,
        monthlyAmount = summaryState.value.thisMonth)

}

@Composable
internal fun IncomeUiScreen(windowWidthSizeClass: WindowWidthSizeClass,
                            incomeList: List<FinanceState>,
                            navigateToAllListScreen: (String) -> Unit,
                            navigateToAddIncomeScreen: (String) -> Unit,
                            currentContent: String,
                            dailyAmount: Double,
                            weeklyAmount: Double,
                            monthlyAmount: Double){

    when(windowWidthSizeClass){
        WindowWidthSizeClass.Compact ->{
            IncomeUiCompact(incomeList = incomeList,
                navigateToAllListScreen = {navigateToAllListScreen(it)},
                navigateToAddIncomeScreen = {navigateToAddIncomeScreen(it)},
                currentContent = currentContent,
                dailyAmount = dailyAmount,
                weeklyAmount = weeklyAmount,
                monthlyAmount = monthlyAmount)
        }

        WindowWidthSizeClass.Medium ->{
            IncomeUiMedium(incomeList = incomeList,
                navigateToAllListScreen = {navigateToAllListScreen(it)},
                navigateToAddIncomeScreen = {navigateToAddIncomeScreen(it)},
                currentContent = currentContent,
                dailyAmount = dailyAmount,
                weeklyAmount = weeklyAmount,
                monthlyAmount = monthlyAmount)
        }
    }

}

@Composable
internal fun IncomeUiCompact(incomeList: List<FinanceState>,
                             navigateToAddIncomeScreen: (String) -> Unit,
                             navigateToAllListScreen:(String) -> Unit,
                             currentContent: String,
                             dailyAmount: Double,
                             weeklyAmount: Double,
                             monthlyAmount: Double){
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp)) {

        TopPartSummaryLayout(
            heightPercentage = .25f,
            dataType = DataTypeForTopPart.Income,
            fontSize = 25.sp,
            dailyAmount = dailyAmount,
            weeklyAmount = weeklyAmount,
            monthlyAmount = monthlyAmount,
            titleFontSize = 30.sp,
            numberFontSize = 35.sp,
            currentContent = currentContent
        )

        Spacer(modifier = Modifier.fillMaxHeight(.15f))

        HistoryLayout(financeList = incomeList,
            heightPercentage = .65f,
            fontSize = 17.sp,
            navigateToAllScreenList = {navigateToAllListScreen(it)},
            financeDataType = DataTypeForTopPart.Income.name)

        Spacer(modifier = Modifier.fillMaxHeight(.35f))

        Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center) {
            Button(onClick = { navigateToAddIncomeScreen(DataTypeForTopPart.Income.name) }, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Add Income", fontSize = 17.sp, modifier = Modifier.padding(8.dp))
            }
        }

    }

}

@Composable
internal fun IncomeUiMedium(incomeList: List<FinanceState>,
                            navigateToAddIncomeScreen: (String) -> Unit,
                            navigateToAllListScreen: (String) -> Unit,
                            currentContent: String,
                            dailyAmount: Double,
                            weeklyAmount: Double,
                            monthlyAmount: Double){
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {

        TopPartSummaryLayout(
            heightPercentage = .25f,
            dataType = DataTypeForTopPart.Income,
            fontSize = 27.sp,
            dailyAmount = dailyAmount,
            weeklyAmount = weeklyAmount,
            monthlyAmount = monthlyAmount,
            titleFontSize = 30.sp,
            numberFontSize = 35.sp,
            currentContent = currentContent
        )

        Spacer(modifier = Modifier.fillMaxHeight(.1f))

        Column(modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(.8f)) {

            Spacer(modifier = Modifier.fillMaxHeight(.1f))

            HistoryLayout(financeList = incomeList,
                heightPercentage = .6f,
                fontSize = 17.sp,
                navigateToAllScreenList = {navigateToAllListScreen(it)},
                financeDataType = DataTypeForTopPart.Income.name)

            Spacer(modifier = Modifier.fillMaxHeight(.3f))

            Button(onClick = { navigateToAddIncomeScreen(DataTypeForTopPart.Income.name) }, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Add Expenses", modifier = Modifier.padding(8.dp))
            }

        }

    }
}

@Composable
@Preview(showBackground = true)
internal fun IncomeUiCompactPreview(){
    AppTheme {
        val list = listOf<FinanceState>()
        
        IncomeUiCompact(incomeList = list,{},{},"",0.0,0.0,0.0)
    }
}

@Composable
@Preview(showBackground = true, device = "spec:width=673dp,height=841dp")
internal fun IncomeUiMediumPreview(){
    AppTheme {
        val list = listOf<FinanceState>()
        
        IncomeUiMedium(incomeList = list,{},{}, "", 0.0,0.0,0.0)
    }

}