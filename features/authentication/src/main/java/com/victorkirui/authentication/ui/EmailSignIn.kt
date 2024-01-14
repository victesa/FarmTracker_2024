package com.victorkirui.authentication.ui

import android.app.Activity
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
internal fun EmailSignInRoute(context: Context, navigateToPhoneAuthenticationScreen: () -> Unit, navigateToSignUpEmail:() -> Unit){
    val windowWidthSizeClass = calculateWindowSizeClass(activity = context as Activity).widthSizeClass

    EmailSignInScreen(windowWidthSizeClass = windowWidthSizeClass, navigateToPhoneAuthenticationScreen = navigateToPhoneAuthenticationScreen, googleSignIn = {}, navigateToSignUpEmail = navigateToSignUpEmail)
}

@Composable
internal fun EmailSignInScreen(windowWidthSizeClass: WindowWidthSizeClass,
                               navigateToPhoneAuthenticationScreen: () -> Unit,
                               navigateToSignUpEmail:() -> Unit,
                               googleSignIn:() -> Unit){
    when(windowWidthSizeClass){
        WindowWidthSizeClass.Compact -> {
            EmailSignInCompact(navigateToPhoneAuthenticationScreen, googleSignIn, navigateToSignUpEmail)
        }

        WindowWidthSizeClass.Medium ->{
            EmailSignInMedium(navigateToPhoneAuthenticationScreen, googleSignIn, navigateToSignUpEmail)
        }
    }
}

@Composable
fun EmailSignInCompact(navigateToPhoneAuthenticationScreen: () -> Unit,
                       googleSignIn:() -> Unit, navigateToSignUpEmail:() -> Unit){
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp)) {
        Spacer(modifier = Modifier.fillMaxHeight(.08f))
        Text(text = "Welcome", fontSize = 36.sp, modifier = Modifier
            .padding(top = 48.dp),
            fontWeight = FontWeight.ExtraBold,
            style = MaterialTheme.typography.headlineLarge)

        Spacer(modifier = Modifier.height(48.dp))

        SignInMainComponentsColumn(fontSize = 16.sp, spacerHeight = 24.dp)

        OrWithText(fontSize = 16.sp)

        OtherOptionsSignInRow(fontSize = 16.sp, navigateToPhoneAuthenticationScreen = navigateToPhoneAuthenticationScreen,
            googleSignIn = googleSignIn)

        SignUpOptionText(fontSize = 14.sp, navigateToSignUpEmail)

    }
}

@Composable
fun EmailSignInMedium(navigateToPhoneAuthenticationScreen: () -> Unit,
                      googleSignIn:() -> Unit, navigateToSignUpEmail:() -> Unit){
    Text(text = "Welcome", fontSize = 40.sp, modifier = Modifier
        .padding(top = 56.dp)
        .padding(horizontal = 24.dp),
        fontWeight = FontWeight.ExtraBold,
        style = MaterialTheme.typography.headlineLarge)

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {

        Spacer(modifier = Modifier.height(48.dp))

        Column(modifier = Modifier.fillMaxWidth(.7f)) {
            SignInMainComponentsColumn(fontSize = 17.sp, spacerHeight = 40.dp)

            OrWithText(fontSize = 17.sp)

            OtherOptionsSignInRow(fontSize = 17.sp, navigateToPhoneAuthenticationScreen = navigateToPhoneAuthenticationScreen,
                googleSignIn = googleSignIn)

            SignUpOptionText(fontSize = 15.sp, navigateToSignUpEmail)
        }
    }
}

@Composable
fun OtherOptionsSignInRow(fontSize: TextUnit,
                          navigateToPhoneAuthenticationScreen: () -> Unit,
                          googleSignIn:() -> Unit){
    Row(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(.5f), verticalAlignment = Alignment.CenterVertically) {
        OtherOptionSignInButtons(icon = Icons.Default.Phone,
            text = "Phone", fontSize = fontSize,
            fillMaxWidthPercentage = .45f,
            onClick = navigateToPhoneAuthenticationScreen)
        Spacer(modifier = Modifier.fillMaxWidth(.1f))
        OtherOptionSignInButtons(icon = Icons.Default.Email,
            text = "Google", fontSize = fontSize, fillMaxWidthPercentage = 1f,
            onClick = googleSignIn)
    }
}

@Composable
fun SignUpOptionText(fontSize: TextUnit, onClick: () -> Unit){
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically) {
        Text(text = "Don't have an account?", color = Color.DarkGray, fontSize = fontSize)
        Spacer(modifier = Modifier.width(8.dp))
        TextButton(onClick = onClick) {
            Text(text = "Sign Up", fontSize = fontSize)
        }
    }
}

@Composable
fun SignInMainComponentsColumn(fontSize: TextUnit, spacerHeight: Dp){
    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(.65f),
        horizontalAlignment = Alignment.End) {

        EmailTextField()

        Spacer(modifier = Modifier.height(spacerHeight))

        PasswordTextField()

        Spacer(modifier = Modifier.height(8.dp))

        ForgotPasswordButton(fontSize)

        Spacer(modifier = Modifier.height(24.dp))

        ButtonSignUp(fontSize = 16.sp)
    }
}

@Composable
fun EmailTextField(){
    OutlinedTextField(value = "", onValueChange = {}, modifier = Modifier.fillMaxWidth(),
        colors = OutlinedTextFieldDefaults.colors(unfocusedContainerColor = Color.White, focusedContainerColor = Color.White),
        placeholder = {
            Text(text = "Email Address")
        }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next),
        keyboardActions = KeyboardActions(onNext = {
            //TODO
        })
    )
}

@Composable
fun PasswordTextField(){
    OutlinedTextField(value = "", onValueChange = {}, modifier = Modifier.fillMaxWidth(),
        colors = OutlinedTextFieldDefaults.colors(unfocusedContainerColor = Color.White, focusedContainerColor = Color.White),
        placeholder = {
            Text(text = "Password")
        }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next),
        keyboardActions = KeyboardActions(onNext = {
            //TODO
        }), visualTransformation = PasswordVisualTransformation(), //trailingIcon = {
        //Icon(painter = painterResource(id = R.drawable.baseline_visibility_24), contentDescription = "Show Password")
        //}
    )
}

@Composable
fun ForgotPasswordButton(fontSize: TextUnit){
    TextButton(onClick = { /*TODO*/ }) {
        Text(text = "Forgot Password?", fontSize = fontSize, color = Color.Black)
    }
}

@Composable
fun ButtonSignUp(fontSize: TextUnit){
    Button(onClick = { /*TODO*/ }, modifier = Modifier
        .fillMaxWidth()
        .height(55.dp)) {
        Text(text = "Log in", fontSize = fontSize)
    }
}

@Composable
fun OrWithText(fontSize: TextUnit){
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
        Divider(modifier = Modifier
            .fillMaxWidth()
            .height(2.dp))
        Text(text = "Or With", fontSize = fontSize, modifier = Modifier
            .background(Color.White)
            .padding(8.dp))
    }
}

@Composable
fun OtherOptionSignInButtons(icon: ImageVector, text: String,
                             fontSize: TextUnit, fillMaxWidthPercentage: Float,
                             onClick:() -> Unit){
    ElevatedButton(onClick = onClick, modifier = Modifier
        .fillMaxWidth(fillMaxWidthPercentage)
        .height(50.dp)) {
        Icon(imageVector = icon, tint = Color.Unspecified,
            contentDescription = "Google Icon", modifier = Modifier.size(18.dp))
        Spacer(modifier = Modifier.width(8.dp))
        Text(text, color = Color.Black, fontSize = fontSize)
    }
}

@Composable
@Preview(showBackground = true)
fun EmailSignInCompactPreview(){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)) {
        EmailSignInCompact({}, {}, {})
    }

}

@Composable
@Preview(showBackground = true, device = "spec:width=673dp,height=841dp")
fun EmailSignInMediumPreview(){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)) {

        EmailSignInMedium({}, {}, {})

    }
}