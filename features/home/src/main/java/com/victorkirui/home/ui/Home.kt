package com.victorkirui.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.victorkirui.home.ui.components.AnimalBriefSection
import com.victorkirui.home.ui.components.DurationRow
import com.victorkirui.home.ui.components.FinanceSummary

@Composable
internal fun HomeRoute(){

}

@Composable
internal fun HomeScreen(){

}

@Composable
fun HomeCompact(){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally) {
        TopBar()

        DurationRow(fontSize = 16.sp)

        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.4f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            FinanceSummary(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.8f)
                    .padding(horizontal = 28.dp)
                    .border(shape = RoundedCornerShape(15.dp), color = Color.Gray, width = 1.dp)
            )
        }

        AnimalBriefSection(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(), fontSize = 16.sp)

    }

}

@Composable
fun TopBar(){
    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(.1f)
        .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center){
        Text(text = "Home", fontSize = 24.sp, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.headlineLarge)
    }
}

@Composable
fun HomeMedium(){

}


@Composable
@Preview(showBackground = true)
fun PreviewHome(){
    HomeCompact()
}
