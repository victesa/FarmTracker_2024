package com.victorkirui.production.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.victorkirui.designsystem.theme.AppTheme
import com.victorkirui.ui.interFontFamily

@Composable
internal fun GoalIndicatorRow(progress: Float,
                              completedGoal: Int,
                              fontSize: TextUnit,
                              heightPercentage: Float,
                              size: Dp,
                              strokeWidth: Dp){
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp)
        .fillMaxHeight(heightPercentage),
        verticalAlignment = Alignment.CenterVertically) {
        GoalProgressIndicator(progress = progress,
            modifier = Modifier
                .size(size)
                .padding(end = 16.dp)
                .padding(top = 8.dp),
            strokeWidth = strokeWidth)

        Text(text = "You have completed ${completedGoal}% of your goal",
            fontFamily = interFontFamily,
            fontSize = fontSize,
            maxLines = 2
        )
    }
}

@Composable
fun GoalProgressIndicator(progress: Float, modifier: Modifier,
                          strokeWidth: Dp){
    Row {
        CircularProgressIndicator(progress = {progress},
            trackColor = Color.Gray,
            modifier = modifier,
            strokeWidth = strokeWidth,
            strokeCap = StrokeCap.Butt)
    }
}



@Composable
@Preview(showBackground = true)
fun GoalProgressIndicatorPreview(){
    AppTheme {
        GoalIndicatorRow(progress = .2f, completedGoal = 20, 16.sp, .2f, size = 85.dp, 10.dp)
    }
}
