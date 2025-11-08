package com.victorkirui.authentication.ui.emailSignUp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.victorkirui.authentication.R
import com.victorkirui.authentication.ui.component.EmailTextField
import com.victorkirui.authentication.ui.component.PasswordTextField
import com.victorkirui.ui.IllustratorLogo
import com.victorkirui.ui.components.GreenButton
import com.victorkirui.ui.components.IndeterminateProgressIndicator


@Composable
internal fun EmailSignUpRoute(viewModel: EmailSignUpViewModel = hiltViewModel(),
                              navigateToProfileGraph:() -> Unit,
                              windowWidthSizeClass: WindowWidthSizeClass){

    val emailSignUpState = viewModel.uiState.collectAsState()
    val emailSignUpEvent = viewModel::onEvent

    if (!emailSignUpState.value.signUpComplete) {
        EmailSignUpScreen(windowWidthSizeClass = windowWidthSizeClass,
            emailValue = emailSignUpState.value.emailAddress,
            onEmailValueChange = {
                emailSignUpEvent(EmailSignUpEvent.EnteredEmailAddress(it))
            },
            passwordValue = emailSignUpState.value.password,
            onPasswordValueChange = {
                emailSignUpEvent(EmailSignUpEvent.EnteredPassword(it))
            },
            confirmPasswordValue = emailSignUpState.value.confirmPassword,
            onConfirmPasswordValueChange = {
                emailSignUpEvent(EmailSignUpEvent.EnteredConfirmPassword(it))
            },
            visualTransformationOne = emailSignUpState.value.visualTransformationOne,
            visualTransformationTwo = emailSignUpState.value.visualTransformationTwo,
            iconOne = emailSignUpState.value.drawableOne,
            onIconOneClicked = {
                emailSignUpEvent(EmailSignUpEvent.ChangePasswordVisibility)
            },
            iconTwo = emailSignUpState.value.drawableTwo, onIconTwoClicked = {
                emailSignUpEvent(
                    EmailSignUpEvent.ChangeConfirmPasswordVisibility
                )
            },
            onSignUpUser = {
                emailSignUpEvent(EmailSignUpEvent.signUpUser)
            }, showProgressIndicator = emailSignUpState.value.showProgressDialog)
    }else{
        navigateToProfileGraph()
        viewModel.showProgressDialogFalse()
    }


}

@Composable
internal fun EmailSignUpScreen(windowWidthSizeClass: WindowWidthSizeClass,
                               emailValue: String, onEmailValueChange: (String) -> Unit,
                               passwordValue: String, onPasswordValueChange: (String) -> Unit,
                               confirmPasswordValue: String, onConfirmPasswordValueChange: (String) -> Unit,
                               visualTransformationOne: VisualTransformation, visualTransformationTwo: VisualTransformation,
                               iconOne: Int, onIconOneClicked:() -> Unit,
                               iconTwo: Int, onIconTwoClicked:() -> Unit,
                               onSignUpUser: () -> Unit,
                               showProgressIndicator: Boolean){
    when(windowWidthSizeClass){
        WindowWidthSizeClass.Compact ->{
            EmailSignUpCompactScreen(emailValue = emailValue, onEmailValueChange = onEmailValueChange,
                passwordValue = passwordValue, onPasswordValueChange = onPasswordValueChange,
                confirmPasswordValue = confirmPasswordValue, onConfirmPasswordValueChange = onConfirmPasswordValueChange,
                visualTransformationOne = visualTransformationOne, visualTransformationTwo = visualTransformationTwo,
                iconOne = iconOne, onIconOneClicked = onIconOneClicked,
                iconTwo = iconTwo, onIconTwoClicked = onIconTwoClicked,
                onSignUpUser = onSignUpUser, showProgressIndicator = showProgressIndicator)
        }

        WindowWidthSizeClass.Medium ->{
            EmailSignUpMediumScreen(emailValue = emailValue, onEmailValueChange = onEmailValueChange,
                passwordValue = passwordValue, onPasswordValueChange = onPasswordValueChange,
                confirmPasswordValue = confirmPasswordValue, onConfirmPasswordValueChange = onConfirmPasswordValueChange,
                visualTransformationOne = visualTransformationOne, visualTransformationTwo = visualTransformationTwo,
                iconOne = iconOne, onIconOneClicked = onIconOneClicked,
                iconTwo = iconTwo, onIconTwoClicked = onIconTwoClicked,
                onSignUpUser = onSignUpUser,
                showProgressIndicator = showProgressIndicator)
        }
    }
}

@Composable
fun EmailSignUpCompactScreen(emailValue: String,
                             onEmailValueChange: (String) -> Unit,
                             passwordValue: String,
                             onPasswordValueChange: (String) -> Unit,
                             confirmPasswordValue: String,
                             onConfirmPasswordValueChange: (String) -> Unit,
                             visualTransformationOne: VisualTransformation,
                             visualTransformationTwo: VisualTransformation,
                             iconOne: Int,
                             onIconOneClicked:() -> Unit,
                             iconTwo: Int,
                             onIconTwoClicked:() -> Unit,
                             onSignUpUser: () -> Unit,
                             showProgressIndicator: Boolean){
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center){
        if (showProgressIndicator){
            Column(modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
                .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 12.dp)
                .background(
                    MaterialTheme.colorScheme.secondaryContainer,
                    shape = RoundedCornerShape(32.dp)
                )
                .padding(10.dp)) {
                TintedColumn(fontSize = 16.sp,
                    emailValue = emailValue,
                    onEmailValueChange = onEmailValueChange,
                    passwordValue = passwordValue,
                    onPasswordValueChange = onPasswordValueChange,
                    confirmPasswordValue = confirmPasswordValue,
                    onConfirmPasswordValueChange = onConfirmPasswordValueChange,
                    visualTransformationOne = visualTransformationOne,
                    visualTransformationTwo = visualTransformationTwo,
                    iconOne = iconOne,
                    onIconOneClicked = onIconOneClicked,
                    iconTwo = iconTwo,
                    onIconTwoClicked = onIconTwoClicked,
                    onClick = onSignUpUser)
            }

            IndeterminateProgressIndicator("Signing Up", 16.sp)
        }else{
            Column(modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
                .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 12.dp)
                .background(
                    MaterialTheme.colorScheme.secondaryContainer,
                    shape = RoundedCornerShape(32.dp)
                )
                .padding(10.dp)) {

                TintedColumn(fontSize = 16.sp,
                    emailValue = emailValue,
                    onEmailValueChange = onEmailValueChange,
                    passwordValue = passwordValue,
                    onPasswordValueChange = onPasswordValueChange,
                    confirmPasswordValue = confirmPasswordValue,
                    onConfirmPasswordValueChange = onConfirmPasswordValueChange,
                    visualTransformationOne = visualTransformationOne,
                    visualTransformationTwo = visualTransformationTwo,
                    iconOne = iconOne,
                    onIconOneClicked = onIconOneClicked,
                    iconTwo = iconTwo,
                    onIconTwoClicked = onIconTwoClicked,
                    onClick = onSignUpUser)

            }
        }
    }
}

@Composable
fun EmailSignUpMediumScreen(emailValue: String, onEmailValueChange: (String) -> Unit,
                            passwordValue: String, onPasswordValueChange: (String) -> Unit,
                            confirmPasswordValue: String, onConfirmPasswordValueChange: (String) -> Unit,
                            visualTransformationOne: VisualTransformation, visualTransformationTwo: VisualTransformation,
                            iconOne: Int, onIconOneClicked:() -> Unit,
                            iconTwo: Int, onIconTwoClicked:() -> Unit,
                            showProgressIndicator: Boolean,
                            onSignUpUser:() -> Unit){

    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center){
        if (showProgressIndicator){
            Column(modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
                .padding(start = 24.dp, end = 24.dp, top = 12.dp, bottom = 12.dp), verticalArrangement = Arrangement.Center) {
                TintedColumn(fontSize = 17.sp,
                    emailValue = emailValue, onEmailValueChange = onEmailValueChange,
                    passwordValue = passwordValue, onPasswordValueChange = onPasswordValueChange,
                    confirmPasswordValue = confirmPasswordValue, onConfirmPasswordValueChange = onConfirmPasswordValueChange,
                    visualTransformationOne = visualTransformationOne, visualTransformationTwo = visualTransformationTwo,
                    iconOne = iconOne, onIconOneClicked = onIconOneClicked,
                    iconTwo = iconTwo, onIconTwoClicked = onIconTwoClicked,
                    onClick = onSignUpUser)
            }
            IndeterminateProgressIndicator(text = "Signing Up", fontSize = 17.sp)
        }else{
            Column(modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
                .padding(start = 24.dp, end = 24.dp, top = 12.dp, bottom = 12.dp), verticalArrangement = Arrangement.Center) {
                TintedColumn(fontSize = 17.sp,
                    emailValue = emailValue, onEmailValueChange = onEmailValueChange,
                    passwordValue = passwordValue, onPasswordValueChange = onPasswordValueChange,
                    confirmPasswordValue = confirmPasswordValue, onConfirmPasswordValueChange = onConfirmPasswordValueChange,
                    visualTransformationOne = visualTransformationOne, visualTransformationTwo = visualTransformationTwo,
                    iconOne = iconOne, onIconOneClicked = onIconOneClicked,
                    iconTwo = iconTwo, onIconTwoClicked = onIconTwoClicked,
                    onClick = onSignUpUser)
            }
        }
    }
}

@Composable
fun TintedColumn(fontSize: TextUnit,
                 emailValue: String, onEmailValueChange: (String) -> Unit,
                 passwordValue: String, onPasswordValueChange: (String) -> Unit,
                 confirmPasswordValue: String, onConfirmPasswordValueChange: (String) -> Unit,
                 visualTransformationOne: VisualTransformation, visualTransformationTwo: VisualTransformation,
                 iconOne: Int, onIconOneClicked:() -> Unit,
                 iconTwo: Int, onIconTwoClicked:() -> Unit,
                 onClick: () -> Unit){
    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        IllustratorLogo(logo = R.drawable.logo_white_2, paddingTop = 32.dp,
            heightPercentage = .3f)

        Text("Create Account", style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold, modifier = Modifier.fillMaxHeight(.1f))

        TextFieldsColumn(emailValue = emailValue, onEmailValueChange = onEmailValueChange,
            passwordValue = passwordValue, onPasswordValueChange = onPasswordValueChange,
            confirmPasswordValue = confirmPasswordValue, onConfirmPasswordValueChange = onConfirmPasswordValueChange,
            visualTransformationOne = visualTransformationOne, visualTransformationTwo = visualTransformationTwo,
            iconOne = iconOne, onIconOneClicked = onIconOneClicked,
            iconTwo = iconTwo, onIconTwoClicked = onIconTwoClicked)

        CreateAccountButton(fontSize = fontSize, onClick = onClick)

        TermsAndConditionComposables(fontSize = fontSize)

    }
}

@Composable
fun CreateAccountButton(fontSize: TextUnit, onClick:() -> Unit){
    Column(modifier = Modifier
        .fillMaxHeight(.5f)
        .fillMaxWidth(), verticalArrangement = Arrangement.Center) {
        GreenButton(onClick = onClick, fontSize = fontSize, text = "Create Account")
    }

}

@Composable
fun TermsAndConditionComposables(fontSize: TextUnit){
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        Text(text = "By creating an account or signing you agree to our Terms and Conditions",
            style = MaterialTheme.typography.bodyMedium, textAlign = TextAlign.Center, fontSize = fontSize)
    }
}

@Composable
fun TextFieldsColumn(emailValue: String, onEmailValueChange: (String) -> Unit,
                     passwordValue: String, onPasswordValueChange: (String) -> Unit,
                     confirmPasswordValue: String, onConfirmPasswordValueChange: (String) -> Unit,
                     visualTransformationOne: VisualTransformation, visualTransformationTwo: VisualTransformation,
                     iconOne: Int, onIconOneClicked:() -> Unit,
                     iconTwo: Int, onIconTwoClicked:() -> Unit){
    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(.5f), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
        EmailTextField(value = emailValue, onValueChange = onEmailValueChange)
        Spacer(modifier = Modifier.height(12.dp))
        PasswordTextField(value = passwordValue, placeholderText = "Password",
            onValueChange = onPasswordValueChange, onIconClicked = onIconOneClicked, icon = iconOne,
            visualTransformation = visualTransformationOne)
        Spacer(modifier = Modifier.height(12.dp))
        PasswordTextField(value = confirmPasswordValue,
            placeholderText = "Confirm Password", onValueChange = onConfirmPasswordValueChange,
            visualTransformation = visualTransformationTwo,
            icon = iconTwo, onIconClicked = onIconTwoClicked)
    }
}

@Composable
@Preview(showBackground = true)
fun Preview(){
    //EmailSignUpCompactScreen()
}

@Composable
@Preview(showBackground = true, device = "spec:width=673dp,height=841dp")
fun PreviewEmailSignUpMediumScreen(){
    //EmailSignUpMediumScreen()
}
