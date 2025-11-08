package com.victorkirui.authentication.ui.resetPassword

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.victorkirui.authentication.R
import com.victorkirui.ui.IllustratorLogo
import com.victorkirui.ui.components.GreenButton

@Composable
internal fun ResetPasswordRoute(){

}

@Composable
internal fun ResetPasswordScreen(){

}

@Composable
fun ResetPasswordCompact(){
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        IllustratorLogo(logo = R.drawable.logocoloured,
            paddingTop = 32.dp, heightPercentage = .3f)

        Spacer(modifier = Modifier.height(32.dp))

        Text(text = "Sign In", fontSize = 36.sp,
            style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.ExtraBold)

        EmailTextFields(value = "", onValueChange = {})

        EmailSignInButton(fontSize = 16.sp)
    }
}

@Composable
fun ResetPasswordMedium(){
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Column(modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(.7f),
            horizontalAlignment = Alignment.CenterHorizontally) {
            IllustratorLogo(logo = R.drawable.logocoloured,
                paddingTop = 32.dp, heightPercentage = .4f)

            Spacer(modifier = Modifier.height(32.dp))

            Text(text = "Sign In", fontSize = 36.sp,
                style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.ExtraBold)

            EmailTextFields(value = "", onValueChange = {})

            EmailSignInButton(fontSize = 17.sp)
        }
    }
}

@Composable
internal fun EmailTextFields(value: String, onValueChange: (String) -> Unit){
    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(.4f), verticalArrangement = Arrangement.Center) {
        EmailTextFields(value = value, onValueChange = onValueChange)
    }

}

@Composable
fun EmailSignInButton(fontSize: TextUnit){
    Column(modifier = Modifier
        .fillMaxHeight(.6f)
        .fillMaxWidth(), verticalArrangement = Arrangement.Center) {
        GreenButton(onClick = { /*TODO*/ }, fontSize = fontSize, text = "Reset Password")
    }

}

@Composable
@Preview(showBackground = true)
internal fun Preview(){
    Column(modifier = Modifier.background(color = Color.White)) {
        ResetPasswordCompact()
    }
}

@Composable
@Preview(showBackground = true, device ="spec:width=673dp,height=841dp")
internal fun PreviewMedium(){
    Column(modifier = Modifier.background(color = Color.White)) {
        ResetPasswordMedium()
    }
}