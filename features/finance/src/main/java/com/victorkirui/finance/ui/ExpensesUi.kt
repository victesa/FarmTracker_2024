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
fun ExpensesUiRoute(viewModel: FinanceViewModel = hiltViewModel(),
                    windowWidthSizeClass: WindowWidthSizeClass,
                    navigateToAllListScreen: (String) -> Unit,
                    navigateToAddIncomeScreen: (String) -> Unit){

    val expensesState = viewModel.getExpensesData2().collectAsState(initial = emptyList())

    val summaryState = viewModel.expenseSummaryState.collectAsState()

    val expensesList by remember(expensesState) {
        mutableStateOf(if (expensesState.value.size > 4){expensesState.value.subList(0,3)}else{expensesState.value})
    }

    ExpensesUiScreen(windowWidthSizeClass = windowWidthSizeClass,
        expensesList = expensesList,
        navigateToAddIncomeScreen = {navigateToAddIncomeScreen(it)},
        navigateToAllListScreen = {navigateToAllListScreen(it)},
        dailyAmount = summaryState.value.today,
        weeklyAmount = summaryState.value.thisWeek,
        monthlyAmount = summaryState.value.thisMonth,
        currentContent = viewModel.currentContentState.value)

}

@Composable
internal fun ExpensesUiScreen(windowWidthSizeClass: WindowWidthSizeClass,
                              expensesList: List<FinanceState>,
                              navigateToAllListScreen: (String) -> Unit,
                              navigateToAddIncomeScreen: (String) -> Unit,
                              currentContent: String,
                              dailyAmount: Double,
                              weeklyAmount: Double,
                              monthlyAmount: Double){
    when(windowWidthSizeClass){
        WindowWidthSizeClass.Compact ->{
            ExpensesUiCompact(expensesList = expensesList,
                navigateToAddIncomeScreen = {navigateToAddIncomeScreen(it)},
                navigateToAllListScreen = {navigateToAllListScreen(it)},
                dailyAmount = dailyAmount,
                weeklyAmount = weeklyAmount,
                monthlyAmount = monthlyAmount,
                currentContent = currentContent)
        }

        WindowWidthSizeClass.Medium ->{
            ExpensesUiMedium(expensesList = expensesList,
                navigateToAddIncomeScreen = {navigateToAddIncomeScreen(it)},
                navigateToAllListScreen = {navigateToAllListScreen(it)},
                dailyAmount = dailyAmount,
                weeklyAmount = weeklyAmount,
                monthlyAmount = monthlyAmount,
                currentContent = currentContent)
        }
    }

}

@Composable
internal fun ExpensesUiCompact(expensesList: List<FinanceState>,
                               navigateToAllListScreen: (String) -> Unit,
                               navigateToAddIncomeScreen: (String) -> Unit,
                               currentContent: String,
                               dailyAmount: Double,
                               weeklyAmount: Double,
                               monthlyAmount: Double){
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp)) {

        TopPartSummaryLayout(
            heightPercentage = .25f,
            dataType = DataTypeForTopPart.Expenses,
            fontSize = 25.sp,
            dailyAmount = dailyAmount,
            weeklyAmount = weeklyAmount,
            monthlyAmount = monthlyAmount,
            titleFontSize = 30.sp,
            numberFontSize = 35.sp,
            currentContent = currentContent
        )

        Spacer(modifier = Modifier.fillMaxHeight(.15f))

        HistoryLayout(financeList = expensesList,
            heightPercentage = .65f,
            fontSize = 17.sp,
            navigateToAllScreenList = {navigateToAllListScreen(it)},
            financeDataType = DataTypeForTopPart.Expenses.name)

        Spacer(modifier = Modifier.fillMaxHeight(.35f))

        Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center) {
            Button(onClick = { navigateToAddIncomeScreen(DataTypeForTopPart.Expenses.name) }, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Add Expense", fontSize = 17.sp, modifier = Modifier.padding(8.dp))
            }
        }

    }

}

@Composable
internal fun ExpensesUiMedium(expensesList: List<FinanceState>,
                              navigateToAllListScreen: (String) -> Unit,
                              navigateToAddIncomeScreen: (String) -> Unit,
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
            dataType = DataTypeForTopPart.Expenses,
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

            HistoryLayout(financeList = expensesList,
                heightPercentage = .6f,
                fontSize = 17.sp,
                navigateToAllScreenList = {navigateToAllListScreen(it)},
                financeDataType = DataTypeForTopPart.Expenses.name)

            Spacer(modifier = Modifier.fillMaxHeight(.3f))

            Button(onClick = { navigateToAddIncomeScreen(DataTypeForTopPart.Expenses.name) }, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Add Expense", modifier = Modifier.padding(8.dp))
            }

        }

    }

}

@Composable
@Preview(showBackground = true)
internal fun ExpensesUiCompactPreview(){
    val list = listOf<FinanceState>()

    AppTheme {
        ExpensesUiCompact(expensesList = list,{},{},"",0.0,0.0,0.0)
    }
}

@Composable
@Preview(showBackground = true, device = "spec:width=673dp,height=841dp")
internal fun ExpensesUiMediumPreview(){
    val list = listOf<FinanceState>()

    AppTheme {
        ExpensesUiMedium(expensesList = list,{},{}, "", 0.0,0.0,0.0)
    }

}