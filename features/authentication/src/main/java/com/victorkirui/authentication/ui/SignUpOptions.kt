package com.victorkirui.authentication.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.victorkirui.authentication.R
import com.victorkirui.ui.IllustratorLogo
import com.victorkirui.ui.TitleParagraph

@Composable
internal fun SignUpOptionsRoute(windowWidthSizeClass: WindowWidthSizeClass,
                                navigateToEmailSignUpScreen:() -> Unit,
                                navigateToPhoneAuthenticationScreen:() -> Unit,
                                navigateToEmailSignInScreen:() -> Unit){

    SignUpOptionsScreen(widthSizeClass = windowWidthSizeClass, {}, navigateToEmailSignUpScreen, navigateToPhoneAuthenticationScreen, navigateToEmailSignInScreen)
}

@Composable
internal fun SignUpOptionsScreen(widthSizeClass: WindowWidthSizeClass,
                                 googleSignIn:() -> Unit,
                                 navigateToEmailSignUpScreen:() -> Unit,
                                 navigateToPhoneAuthenticationScreen:() -> Unit,
                                 navigateToEmailSignInScreen:() -> Unit){

    when(widthSizeClass){
        WindowWidthSizeClass.Compact ->{
            SignUpOptionsCompactScreen(googleSignIn, navigateToEmailSignUpScreen, navigateToPhoneAuthenticationScreen, navigateToEmailSignInScreen)
        }

        WindowWidthSizeClass.Medium ->{
            SignUpOptionsMediumScreen(googleSignIn, navigateToEmailSignUpScreen, navigateToPhoneAuthenticationScreen, navigateToEmailSignInScreen)
        }
    }

}

@Composable
fun SignUpOptionsCompactScreen(googleSignIn:() -> Unit,
                               navigateToEmailSignUpScreen:() -> Unit,
                               navigateToPhoneAuthenticationScreen:() -> Unit,
                               navigateToEmailSignInScreen:() -> Unit){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)
        .padding(start = 16.dp, end = 16.dp)) {
        TintedColumn(horizontalPadding = 10.dp,
            logo = R.drawable.logowhite, signUpButtonTextSize = 16.sp,
            googleSignIn = googleSignIn, navigateToEmailSignUpScreen = navigateToEmailSignUpScreen,
            navigateToPhoneAuthenticationScreen = navigateToPhoneAuthenticationScreen)

        SignInOptionTexts(fontSize = 16.sp, onClick = navigateToEmailSignInScreen)

    }
}

@Composable
fun SignUpOptionsMediumScreen(googleSignIn:() -> Unit,
                              navigateToEmailSignUpScreen:() -> Unit,
                              navigateToPhoneAuthenticationScreen:() -> Unit,
                              navigateToEmailSignInScreen:() -> Unit){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)
        .padding(start = 24.dp, end = 24.dp, top = 16.dp)
    ) {
        TintedColumn(horizontalPadding = 40.dp, logo = R.drawable.logowhite, 17.sp,
            googleSignIn = googleSignIn, navigateToEmailSignUpScreen = navigateToEmailSignUpScreen,
            navigateToPhoneAuthenticationScreen = navigateToPhoneAuthenticationScreen)
        SignInOptionTexts(16.sp, onClick = navigateToEmailSignInScreen)

    }
}

@Composable
fun TintedColumn(horizontalPadding: Dp, logo: Int,
                 signUpButtonTextSize: TextUnit,
                 googleSignIn:() -> Unit,
                 navigateToEmailSignUpScreen:() -> Unit,
                 navigateToPhoneAuthenticationScreen:() -> Unit){
    Column(modifier = Modifier
        .background(
            color = MaterialTheme.colorScheme.secondaryContainer,
            shape = RoundedCornerShape(32.dp)
        )
        .fillMaxWidth()
        .fillMaxHeight(.85f)
        .padding(horizontal = horizontalPadding)) {

        //Component in core:Ui module
        IllustratorLogo(logo = logo,
            paddingTop = 32.dp, heightPercentage = .4f)

        //Component in core:Ui module
        TitleParagraph(style = MaterialTheme.typography.headlineLarge, maxHeight = .35f, fontSize = 16.sp)

        SignUpButtonsColumn(signUpButtonTextSize = signUpButtonTextSize,
            googleSignIn = googleSignIn, navigateToEmailSignUpScreen = navigateToEmailSignUpScreen,
            navigateToPhoneAuthenticationScreen = navigateToPhoneAuthenticationScreen)

    }
}



@Composable
fun SignUpButtonsColumn(signUpButtonTextSize: TextUnit, googleSignIn:() -> Unit,
                        navigateToEmailSignUpScreen:() -> Unit,
                        navigateToPhoneAuthenticationScreen:() -> Unit){
    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .padding(bottom = 24.dp), verticalArrangement = Arrangement.Center) {

        SignUpButtonOptions(fontSize = signUpButtonTextSize, onClick = googleSignIn)

        Spacer(modifier = Modifier.height(18.dp))

        SignUpButtonIconOptions(icon = Icons.Default.Email, text = "Continue with Email",
            fontSize = signUpButtonTextSize, onClick = navigateToEmailSignUpScreen)

        Spacer(modifier = Modifier.height(18.dp))

        SignUpButtonIconOptions(icon = Icons.Default.Phone, text = "Continue with Phone",
            fontSize = signUpButtonTextSize, onClick = navigateToPhoneAuthenticationScreen)
    }
}

@Composable
fun SignUpButtonOptions(fontSize: TextUnit, onClick:() -> Unit){
    ElevatedButton(onClick = onClick, modifier = Modifier
        .fillMaxWidth()
        .height(50.dp)) {
        Icon(painter = painterResource(id = R.drawable.google), tint = Color.Unspecified,
            contentDescription = "Google Icon", modifier = Modifier.size(18.dp))
        Spacer(modifier = Modifier.width(8.dp))
        Text("Continue with Google", color = Color.Black, fontSize = fontSize)
    }
}

@Composable
fun SignInOptionTexts(fontSize: TextUnit, onClick: () -> Unit){
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically) {
        Text(text = "Already have an account?", color = Color.DarkGray, fontSize = fontSize)
        Spacer(modifier = Modifier.width(8.dp))
        TextButton(onClick = onClick) {
            Text(text = "Log in", fontSize = fontSize)
        }
    }
}

@Composable
fun SignUpButtonIconOptions(icon: ImageVector, text: String, fontSize: TextUnit, onClick: () -> Unit){
    ElevatedButton(onClick = onClick, modifier = Modifier
        .fillMaxWidth()
        .height(50.dp)) {
        Icon(imageVector = icon, tint = Color.Unspecified,
            contentDescription = "Google Icon", modifier = Modifier.size(18.dp))
        Spacer(modifier = Modifier.width(8.dp))
        Text(text, color = Color.Black, fontSize = fontSize)
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewCompact(){
    SignUpOptionsCompactScreen({},{},{},{})

}

@Composable
@Preview(showBackground = true, device = "spec:width=673dp,height=841dp")
fun PreviewMedium(){
    SignUpOptionsMediumScreen({},{},{},{})

}
