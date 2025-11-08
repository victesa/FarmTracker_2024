package com.victorkirui.finance.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.victorkirui.designsystem.theme.AppTheme
import com.victorkirui.finance.domain.FinanceState

@Composable
fun HistoryLayout(
    financeList: List<FinanceState>,
    heightPercentage: Float,
    fontSize: TextUnit,
    navigateToAllScreenList: (String) -> Unit,
    financeDataType: String
){

    var financeListNew by remember(financeList) {
        financeList
    }

    if (financeList.isEmpty()){
        OutlinedCard(modifier = Modifier
            .fillMaxHeight(heightPercentage)) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "No Data", fontSize = fontSize)
            }
        }
    }else{
        OutlinedCard(shape = RoundedCornerShape(20.dp)) {
            LazyColumn{
                item {
                    Text(text = "History", modifier = Modifier.padding(8.dp), fontWeight = FontWeight.Bold)
                    HorizontalDivider()
                }

                items(financeList.size){
                    OneHistoryColumn(produceName = financeList[it].name,
                        produceDate = financeList[it].date,
                        produceUnit = financeList[it].quantity)
                    HorizontalDivider()
                }
                item {
                    Row(modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End) {
                        TextButton(onClick = { navigateToAllScreenList(financeDataType) }) {
                            Text(text = "See All")
                        }
                    }
                }
            }
        }
    }





}

@Composable
fun OneHistoryColumn(produceName: String,
                     produceDate: String,
                     produceUnit: String){
    ListItem(headlineContent = {
        Text(text = produceName)
    },
        supportingContent = {
            Text(text = produceDate)
        },
        trailingContent = {
            Text(text = produceUnit)
        })

}

@Composable
@Preview(showBackground = true)
fun PreviewHLCompact(){
    AppTheme {

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.Center) {
            val list = listOf<FinanceState>()
            HistoryLayout(list, .4f, 17.sp,{},"")
        }

    }
}