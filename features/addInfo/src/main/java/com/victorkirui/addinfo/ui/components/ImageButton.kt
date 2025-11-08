package com.victorkirui.addinfo.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.victorkirui.addinfo.R
import com.victorkirui.designsystem.theme.AppTheme

@Composable
fun ImageButton(
    image: Int,
    text: String,
    fontSize: TextUnit,
    onClick:() -> Unit,
    widthPercentage: Float
){
    Box(contentAlignment = Alignment.BottomEnd,
        modifier = Modifier.clickable {
            onClick()
        }) {
        Image(painter = painterResource(id = image),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(widthPercentage)
                .height(150.dp),
            contentScale = ContentScale.FillBounds)

        Row(modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = text,
                color = Color.White,
                fontSize = fontSize)

            Icon(imageVector = Icons.AutoMirrored.Default.ArrowForward,
                contentDescription = null,
                tint = Color.White)
        }
    }
}

@Composable
@Preview(showBackground = true)
internal fun Preview(){
    AppTheme {
        ImageButton(
            image = R.drawable.expenses_pic,
            text = "Animals",
            fontSize = 20.sp,{},
            1f
        )
    }
}