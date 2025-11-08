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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.victorkirui.authentication.R
import com.victorkirui.ui.IllustratorLogo
import com.victorkirui.ui.TitleParagraph
import com.victorkirui.ui.components.GreenButton

@Composable
internal fun OnBoardingRoute(onSignInClicked: () -> Unit,
                             onSignUpClicked: () -> Unit,
                             windowWidthSizeClass: WindowWidthSizeClass){

    OnBoardingScreen(widthSizeClass = windowWidthSizeClass, onSignUpClicked = onSignUpClicked, onSignInClicked = onSignInClicked)
}

@Composable
internal fun OnBoardingScreen(widthSizeClass: WindowWidthSizeClass, onSignUpClicked:() -> Unit, onSignInClicked:() -> Unit){
    when(widthSizeClass){
        WindowWidthSizeClass.Compact ->{
            OnBoardingCompactScreen(onSignInClicked = onSignInClicked, onSignUpClicked = onSignUpClicked)
        }
        WindowWidthSizeClass.Medium ->{
            OnBoardingMediumScreen(onSignInClicked,onSignUpClicked)
        }
    }
}

@Composable
fun OnBoardingCompactScreen(onSignUpClicked: () -> Unit, onSignInClicked: () -> Unit){
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(start = 16.dp, end = 16.dp)) {

        //Component in core:Ui module
        IllustratorLogo(logo = R.drawable.logocoloured, paddingTop = 52.dp,
            heightPercentage = .4f)

        //Component in core:Ui module
        TitleParagraph(style = MaterialTheme.typography.headlineLarge, maxHeight = .4f, fontSize = 16.sp)

        OnBoardingButtons(55.dp, onSignInClicked, onSignUpClicked)

    }
}

@Composable
fun OnBoardingMediumScreen(onSignInClicked: () -> Unit, onSignUpClicked: () -> Unit){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White), horizontalAlignment = Alignment.CenterHorizontally) {
        Column(modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(.8f)
            .background(color = Color.White)
            .padding(start = 24.dp, end = 24.dp)) {

            //Component in core:Ui module
            IllustratorLogo(logo = R.drawable.logocoloured, paddingTop = 52.dp,
                heightPercentage = .4f)

            //Component in core:Ui module
            TitleParagraph(style = MaterialTheme.typography.headlineLarge, maxHeight = .4f, fontSize = 24.sp)

            OnBoardingButtons(55.dp, onSignUpClicked = onSignUpClicked, onSignInClicked = onSignInClicked)



        }
    }

}


@Composable
fun OnBoardingButtons(height: Dp, onSignInClicked: () -> Unit, onSignUpClicked: () -> Unit){
    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(),
        verticalArrangement = Arrangement.Center) {
        GreenButton(onClick = onSignInClicked,
            fontSize = 16.sp, text = "Sign In")

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedButton(onClick = onSignUpClicked, modifier = Modifier
            .fillMaxWidth()
            .height(height), colors = ButtonDefaults.buttonColors(containerColor = Color.White,
            contentColor = MaterialTheme.colorScheme.primary)
        ) {
            Text(text = "Create Account")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCompactB(){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp)
                .background(color = Color.White)) {
            IllustratorLogo(logo = R.drawable.illustrationlogo, paddingTop = 52.dp, heightPercentage = .5f)

            TitleParagraph(style = MaterialTheme.typography.headlineLarge, maxHeight = .4f, fontSize = 16.sp)

            OnBoardingButtons(55.dp,{}, {})
        }
    }
}

@Preview(showBackground = true, device = "spec:width=673dp,height=841dp")
@Composable
fun PreviewMediumA(){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)) {
        OnBoardingMediumScreen(onSignInClicked = { /*TODO*/ }) {

        }
    }
}