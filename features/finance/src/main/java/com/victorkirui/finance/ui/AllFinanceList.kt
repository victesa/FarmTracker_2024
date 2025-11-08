package com.victorkirui.finance.ui

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
import com.victorkirui.finance.domain.DataTypeForTopPart
import com.victorkirui.finance.domain.FinanceState

@Composable
fun AllFinanceListRoute(viewModel: FinanceViewModel = hiltViewModel(),
                        windowWidthSizeClass: WindowWidthSizeClass,
                        navigateToAddFinance:(String) -> Unit,
                        financeType: String){

    val uiState = if (financeType == DataTypeForTopPart.Expenses.name){viewModel.expensesState.collectAsState()}else{viewModel.expensesState.collectAsState()}

    AllFinanceListScreen(windowWidthSizeClass = windowWidthSizeClass,
        financeList = uiState.value,
        addFinance = {navigateToAddFinance(financeType)})

}

@Composable
internal fun AllFinanceListScreen(windowWidthSizeClass: WindowWidthSizeClass,
                                  financeList: List<FinanceState>,
                                  addFinance: () -> Unit){
    when(windowWidthSizeClass){
        WindowWidthSizeClass.Compact ->{
            AllFinanceListCompact(addFinance = { addFinance() },
                financeList = financeList)
        }

        WindowWidthSizeClass.Medium ->{
            AllFinanceListMedium(addFinance = { addFinance() },
                financeList = financeList)
        }
    }

}

@Composable
internal fun AllFinanceListCompact(addFinance:() -> Unit,
                                   financeList: List<FinanceState>){
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { addFinance() }) {
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
                items(financeList.size){
                    ElevatedCard(modifier = Modifier.padding(top = 8.dp)) {
                        ListItem(headlineContent = {
                            Text(text = financeList[it].name)
                        },
                            supportingContent = {
                                Text(text = financeList[it].date)
                            },
                            trailingContent = {
                                Text(text = financeList[it].quantity)
                            })
                    }

                }
            }

        }
    }
}

@Composable
internal fun AllFinanceListMedium(addFinance: () -> Unit,
                                  financeList: List<FinanceState>){
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { addFinance() }) {
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
                items(financeList.size){
                    ElevatedCard(modifier = Modifier.padding(top = 8.dp)) {
                        ListItem(headlineContent = {
                            Text(text = financeList[it].name)
                        },
                            supportingContent = {
                                Text(text = financeList[it].date)
                            },
                            trailingContent = {
                                Text(text = financeList[it].quantity)
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
internal fun AllFinanceListCompactPreview(){
    AppTheme {
        val list = listOf<FinanceState>()
        AllFinanceListCompact(addFinance = { /*TODO*/ }, financeList = list)
    }
}

@Composable
@Preview(showBackground = true, device = "spec:width=673dp,height=841dp")
internal fun AllFinanceListMediumPreview(){
    AppTheme {
        val list = listOf<FinanceState>()
        AllFinanceListMedium(addFinance = { /*TODO*/ }, financeList = list)
    }

}