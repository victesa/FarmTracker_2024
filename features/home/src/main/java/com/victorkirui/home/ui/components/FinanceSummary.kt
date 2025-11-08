package com.victorkirui.home.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FinanceSummary(modifier: Modifier,
                   showEmptyMessage: Boolean,
                   fontSize: TextUnit){
    if (showEmptyMessage){
        Column(modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Text(text = "You haven't added any data. Click here to add", fontSize = fontSize,
                modifier = Modifier.padding(16.dp))
        }
    }else{
        Column(modifier = modifier){

            DetailsColumn(title = "Net Income",
                figure = "+20,000",
                figureColor = Color.Green,
                fontSize = 20.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.55f))

            Divider(modifier = Modifier
                .fillMaxWidth()
                .height(1.dp), color = Color.Gray)

            Row(modifier = Modifier.fillMaxSize()) {
                DetailsColumn(title = "Expenses",
                    figure = "10,000",
                    figureColor = Color.Red,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .fillMaxWidth(.49f)
                        .fillMaxHeight())

                Divider(modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp), color = Color.Gray)

                DetailsColumn(title ="Profit" ,
                    figure = "30,000",
                    figureColor = Color.Green,
                    fontSize = 20.sp,
                    modifier = Modifier.fillMaxSize())
            }

        }
    }


}

@Composable
private fun DetailsColumn(title: String,
                          figure: String,
                          figureColor: Color,
                          fontSize: TextUnit, modifier: Modifier){
    Column(modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = title,
            fontSize = fontSize,
            style = MaterialTheme.typography.headlineLarge,
            color = Color.Black)

        Text(text = figure,
            fontSize = fontSize,
            style = MaterialTheme.typography.headlineLarge,
            color = figureColor)
    }
}

@Composable
@Preview
fun PreviewFinance(){
    FinanceSummary(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(.8f)
        .border(shape = RoundedCornerShape(15.dp), color = Color.Gray, width = 1.dp)
        .background(Color.White),
        false,
        16.sp)
}