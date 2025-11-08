package com.victorkirui.authentication.ui

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
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.joelkanyi.jcomposecountrycodepicker.component.KomposeCountryCodePicker
import com.victorkirui.ui.IllustratorLogo
import com.victorkirui.ui.components.GreenButton


@Composable
internal fun PhoneAuthenticationRoute(windowWidthSizeClass: WindowWidthSizeClass){

    PhoneAuthenticationScreen(windowWidthSizeClass = windowWidthSizeClass)
}

@Composable
internal fun PhoneAuthenticationScreen(windowWidthSizeClass: WindowWidthSizeClass){
    when(windowWidthSizeClass){
        WindowWidthSizeClass.Compact -> {
            PhoneAuthenticationCompact()
        }

        WindowWidthSizeClass.Medium -> {
            PhoneAuthenticationMedium()
        }
    }
}

@Composable
fun PhoneAuthenticationCompact(){
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        IllustratorLogo(logo = com.victorkirui.authentication.R.drawable.logocoloured,
            paddingTop = 32.dp, heightPercentage = .3f)

        Spacer(modifier = Modifier.height(32.dp))

        Text(text = "Sign In", fontSize = 36.sp,
            style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.ExtraBold)

        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.4f),
            verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            KomposeCountryCodePicker(
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(text = "78940483")
                },
                showCountryCode = true,
                showCountryFlag = true
            )

            Text(text = "International carrier charges may apply", fontSize = 12.sp,
                color = Color.DarkGray, modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                textAlign = TextAlign.Center)
        }
        PhoneSignInButton(fontSize = 16.sp)
    }
}

@Composable
fun PhoneAuthenticationMedium(){
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Column(modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(.7f),
            horizontalAlignment = Alignment.CenterHorizontally) {
            IllustratorLogo(logo = com.victorkirui.authentication.R.drawable.logocoloured,
                paddingTop = 32.dp, heightPercentage = .4f)

            Spacer(modifier = Modifier.height(32.dp))

            Text(text = "Sign In", fontSize = 36.sp,
                style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.ExtraBold)

            Column(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.4f),
                verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                KomposeCountryCodePicker(
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {
                        Text(text = "78940483")
                    },
                    showCountryCode = true,
                    showCountryFlag = true
                )

                Text(text = "International carrier charges may apply", fontSize = 14.sp,
                    color = Color.DarkGray, modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 17.dp),
                    textAlign = TextAlign.Center)
            }
            PhoneSignInButton(fontSize = 17.sp)
        }
    }
}

@Composable
fun PhoneSignInButton(fontSize: TextUnit){
    Column(modifier = Modifier
        .fillMaxHeight(.6f)
        .fillMaxWidth(), verticalArrangement = Arrangement.Center) {
        GreenButton(onClick = { /*TODO*/ }, fontSize = fontSize, text = "Sign Up")
    }

}

@Composable
@Preview(showBackground = true)
fun PhoneAuthenticationCompactPreview(){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)) {
        PhoneAuthenticationCompact()
    }
}

@Composable
@Preview(showBackground = true, device = "spec:width=673dp,height=841dp")
fun PhoneAuthenticationMediumPreview(){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)) {
        PhoneAuthenticationMedium()
    }

}