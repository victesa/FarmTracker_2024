package com.victorkirui.authentication.ui.emailSignIn

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.victorkirui.authentication.ui.component.EmailTextField
import com.victorkirui.authentication.ui.component.PasswordTextField
import com.victorkirui.ui.components.GreenButton


@Composable
internal fun EmailSignInRoute(windowWidthSizeClass: WindowWidthSizeClass,
                              navigateToPhoneAuthenticationScreen: () -> Unit,
                              navigateToSignUpEmail:() -> Unit,
                              viewModel: EmailSignInViewModel = hiltViewModel(),
                              navigateToHomeScreen:()-> Unit){

    val emailSignInState = viewModel.uiState.collectAsState()
    val emailSignInEvent = viewModel::onEvent
    val context = LocalContext.current

    EmailSignInScreen(windowWidthSizeClass = windowWidthSizeClass,
        navigateToPhoneAuthenticationScreen = navigateToPhoneAuthenticationScreen,
        googleSignIn = {}, navigateToSignUpEmail = navigateToSignUpEmail,
        emailValue = emailSignInState.value.emailAddress, onEmailValueChanged = {
            emailSignInEvent(
                EmailSignInEvent.EnteredEmailAddress(it)
            )},
        passwordValue = emailSignInState.value.password, onPasswordValueChanged = {
            emailSignInEvent(EmailSignInEvent.EnteredPassword(it))
        },
        icon = emailSignInState.value.drawable, onIconClicked = {
            emailSignInEvent(EmailSignInEvent.ChangePasswordVisibility)},
        visualTransformation = emailSignInState.value.visualTransformation,
        onSignInClicked = {emailSignInEvent(
            EmailSignInEvent.SignInUser(context, navigateToHomeScreen)
        )})
}

@Composable
internal fun EmailSignInScreen(windowWidthSizeClass: WindowWidthSizeClass,
                               navigateToPhoneAuthenticationScreen: () -> Unit,
                               navigateToSignUpEmail:() -> Unit,
                               googleSignIn:() -> Unit,
                               onSignInClicked: () -> Unit,
                               emailValue: String, onEmailValueChanged:(String) -> Unit,
                               passwordValue: String, onPasswordValueChanged:(String) -> Unit,
                               icon: Int, onIconClicked: () -> Unit, visualTransformation: VisualTransformation){
    when(windowWidthSizeClass){
        WindowWidthSizeClass.Compact -> {
            EmailSignInCompact(navigateToPhoneAuthenticationScreen, googleSignIn, navigateToSignUpEmail,
                emailValue = emailValue, onEmailValueChanged = onEmailValueChanged,
                passwordValue = passwordValue, onPasswordValueChanged = onPasswordValueChanged,
                icon = icon, onIconClicked = onIconClicked, visualTransformation = visualTransformation, onSignInClicked = onSignInClicked)
        }

        WindowWidthSizeClass.Medium ->{
            EmailSignInMedium(navigateToPhoneAuthenticationScreen, googleSignIn, navigateToSignUpEmail,
                emailValue = emailValue, onEmailValueChanged = onEmailValueChanged,
                passwordValue = passwordValue, onPasswordValueChanged = onPasswordValueChanged,
                icon = icon, onIconClicked = onIconClicked, visualTransformation = visualTransformation, onSignInClicked = onSignInClicked)
        }
    }
}

@Composable
fun EmailSignInCompact(navigateToPhoneAuthenticationScreen: () -> Unit,
                       googleSignIn:() -> Unit, navigateToSignUpEmail:() -> Unit,
                       emailValue: String, onEmailValueChanged:(String) -> Unit,
                       passwordValue: String, onPasswordValueChanged:(String) -> Unit,
                       onSignInClicked: () -> Unit,
                       icon: Int, onIconClicked: () -> Unit, visualTransformation: VisualTransformation){
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp)) {
        Spacer(modifier = Modifier.fillMaxHeight(.08f))
        Text(text = "Welcome", fontSize = 36.sp, modifier = Modifier
            .padding(top = 48.dp),
            fontWeight = FontWeight.ExtraBold,
            style = MaterialTheme.typography.headlineLarge)

        Spacer(modifier = Modifier.height(48.dp))

        SignInMainComponentsColumn(fontSize = 16.sp, spacerHeight = 24.dp,
            emailValue = emailValue, onEmailValueChanged = onEmailValueChanged,
            passwordValue = passwordValue, onPasswordValueChanged = onPasswordValueChanged,
            icon = icon, onIconClicked = onIconClicked, visualTransformation = visualTransformation, onSignInClicked = onSignInClicked)

        OrWithText(fontSize = 16.sp)

        OtherOptionsSignInRow(fontSize = 16.sp, navigateToPhoneAuthenticationScreen = navigateToPhoneAuthenticationScreen,
            googleSignIn = googleSignIn)

        SignUpOptionText(fontSize = 14.sp, navigateToSignUpEmail)

    }
}

@Composable
fun EmailSignInMedium(navigateToPhoneAuthenticationScreen: () -> Unit,
                      googleSignIn:() -> Unit, navigateToSignUpEmail:() -> Unit,
                      emailValue: String, onEmailValueChanged:(String) -> Unit,
                      passwordValue: String, onPasswordValueChanged:(String) -> Unit,
                      icon: Int, onIconClicked: () -> Unit,visualTransformation: VisualTransformation,
                      onSignInClicked: () -> Unit){
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
            SignInMainComponentsColumn(fontSize = 17.sp, spacerHeight = 40.dp,
                emailValue = emailValue, onEmailValueChanged = onEmailValueChanged,
                passwordValue = passwordValue, onPasswordValueChanged = onPasswordValueChanged,
                icon = icon, onIconClicked = onIconClicked, visualTransformation = visualTransformation, onSignInClicked = onSignInClicked)

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
fun SignInMainComponentsColumn(
    emailValue: String, onEmailValueChanged:(String) -> Unit,
    passwordValue: String, onPasswordValueChanged:(String) -> Unit,
    icon: Int, onIconClicked: () -> Unit,
    visualTransformation: VisualTransformation,
    fontSize: TextUnit, spacerHeight: Dp,
    onSignInClicked: () -> Unit){
    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(.65f),
        horizontalAlignment = Alignment.End) {

        EmailTextField(value = emailValue, onValueChange = onEmailValueChanged)

        Spacer(modifier = Modifier.height(spacerHeight))

        PasswordTextField(value = passwordValue, onValueChange = onPasswordValueChanged, icon = icon,
            onIconClicked = onIconClicked, visualTransformation = visualTransformation, placeholderText = "Password")

        Spacer(modifier = Modifier.height(8.dp))

        ForgotPasswordButton(fontSize)

        Spacer(modifier = Modifier.height(24.dp))

        GreenButton(onClick = onSignInClicked, fontSize = 16.sp, text = "Sign In" )
    }
}




@Composable
fun ForgotPasswordButton(fontSize: TextUnit){
    TextButton(onClick = { /*TODO*/ }) {
        Text(text = "Forgot Password?", fontSize = fontSize, color = Color.Black)
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
        //EmailSignInCompact({}, {}, {})
    }

}

@Composable
@Preview(showBackground = true, device = "spec:width=673dp,height=841dp")
fun EmailSignInMediumPreview(){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)) {

        //EmailSignInMedium({}, {}, {})

    }
}