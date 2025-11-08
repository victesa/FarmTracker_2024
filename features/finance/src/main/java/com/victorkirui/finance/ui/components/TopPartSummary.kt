package com.victorkirui.finance.ui.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.animation.with
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.victorkirui.designsystem.theme.AppTheme
import com.victorkirui.finance.domain.DataTypeForTopPart
import com.victorkirui.finance.ui.FinanceViewModel
import java.text.NumberFormat
import java.util.Locale

@Composable
fun TopPartSummaryLayout(heightPercentage: Float,
                         dataType: DataTypeForTopPart,
                         fontSize: TextUnit,
                         dailyAmount: Double,
                         weeklyAmount: Double,
                         monthlyAmount: Double,
                         titleFontSize: TextUnit,
                         numberFontSize: TextUnit,
                         currentContent: String){
    Column(modifier = Modifier
        .fillMaxHeight(heightPercentage)
        .fillMaxWidth()
        .padding(top = 24.dp),
        verticalArrangement = Arrangement.SpaceBetween) {

        Text(text = dataType.name,
            fontSize = titleFontSize,
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold)



        AnimatedContent(targetState = currentContent,
            label = "",
            transitionSpec = {
                slideInHorizontally { height -> height } + fadeIn() togetherWith
                        slideOutHorizontally { height -> -height } + fadeOut()
            }) {

            when(it){
                "Today" ->{
                    TodayData(dataType = dataType,
                        fontSize = fontSize,
                        amount = dailyAmount,
                        numberFontSize = numberFontSize)

                }

                "ThisWeek" ->{
                    ThisWeekData(dataType = dataType,
                        fontSize = fontSize,
                        amount = weeklyAmount,
                        numberFontSize = numberFontSize)
                }

                "ThisMonth" ->{
                    ThisMonthData(dataType = dataType,
                        fontSize = fontSize,
                        amount = monthlyAmount,
                        numberFontSize = numberFontSize)
                }
            }

        }

    }

}

@Composable
internal fun TodayData(dataType: DataTypeForTopPart,
                       fontSize: TextUnit,
                       numberFontSize: TextUnit,
                       amount: Double){
    Column(modifier = Modifier.fillMaxWidth(), 
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Total ${dataType.name} Today",
            fontSize = fontSize,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.headlineLarge)

        Text(text = separateNumber(amount),
            color = if (dataType.name == DataTypeForTopPart.Income.name){
                Color.Green}else{Color.Red},
            fontSize = numberFontSize,
            fontWeight = FontWeight.Bold)
    }
}

@Composable
internal fun ThisWeekData(dataType: DataTypeForTopPart,
                          fontSize: TextUnit,
                          numberFontSize: TextUnit,
                          amount: Double){
    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Total ${dataType.name} This Week",
            fontSize = fontSize,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.headlineLarge)

        Text(text = separateNumber(amount),
            color = if (dataType.name == DataTypeForTopPart.Income.name){
                Color.Green}else{Color.Red},
            fontSize = numberFontSize,
            fontWeight = FontWeight.Bold)
    }
}

@Composable
internal fun ThisMonthData(dataType: DataTypeForTopPart,
                          fontSize: TextUnit,
                          amount: Double,
                           numberFontSize: TextUnit,){
    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Total ${dataType.name} This Month",
            fontSize = fontSize,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.headlineLarge)

        Text(text = separateNumber(amount),
            color = if (dataType.name == DataTypeForTopPart.Income.name){
                Color.Green}else{Color.Red},
            fontSize = numberFontSize,
            fontWeight = FontWeight.Bold)
    }
}

fun separateNumber(amount: Double): String{
    val numberFormat = NumberFormat.getNumberInstance(Locale.US)
    return numberFormat.format(amount)
}

@Composable
@Preview(showBackground = true)
internal fun TopPartPreview(){
    AppTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            TopPartSummaryLayout(
                heightPercentage = .2f,
                dataType = DataTypeForTopPart.Income,
                fontSize = 30.sp,
                dailyAmount = 10000.0,
                weeklyAmount = 10000.0,
                monthlyAmount = 200000.0,
                numberFontSize = 35.sp,
                titleFontSize = 35.sp,
                currentContent = "Today"
            )
        }
    }
}