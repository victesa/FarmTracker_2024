package com.victorkirui.home.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.victorkirui.home.ui.HomeViewModel

@Composable
fun DurationRow(fontSize: TextUnit,
                viewModel: HomeViewModel = hiltViewModel()){
    val periodList = listOf("Daily", "Weekly", "Monthly")
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp)
    ){
        items(3){
            DurationButton(text = periodList[it],
                fontSize = fontSize,
                isSelected = viewModel.selectedPeriod.collectAsState().value == periodList[it],
                onClick = { viewModel.onPeriodClicked(periodList[it]) }
            )

        }
    }
}

@Composable
fun DurationButton(text: String,
                   fontSize: TextUnit,
                   isSelected: Boolean,
                   onClick:() -> Unit){
    Column(modifier = Modifier
        .background(
            color = if (isSelected) {
                MaterialTheme.colorScheme.primary
            } else {
                Color(0xFFF7F7F7)
            },
            shape = RoundedCornerShape(15.dp)
        )
        .clickable { onClick() },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = text,
            fontSize = fontSize,
            modifier = Modifier
                .padding(horizontal = 40.dp)
                .padding(vertical = 18.dp),
            color = if (isSelected){
                Color.White
            }else{Color.Black})
    }
}

@Composable
@Preview(showBackground = true)
fun Preview(){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)) {
        DurationRow(fontSize = 16.sp)
    }
}