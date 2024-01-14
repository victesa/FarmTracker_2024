package com.victorkirui.authentication.ui

import android.app.Activity
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
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
import com.victorkirui.authentication.R
import com.victorkirui.ui.IllustratorLogo


    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    @Composable
    internal fun EmailSignUpRoute(context: Context){
        val windowWidthSizeClass = calculateWindowSizeClass(activity = context as Activity).widthSizeClass

        EmailSignUpScreen(windowWidthSizeClass = windowWidthSizeClass)
    }

    @Composable
    fun EmailSignUpScreen(windowWidthSizeClass: WindowWidthSizeClass){
        when(windowWidthSizeClass){
            WindowWidthSizeClass.Compact ->{
                EmailSignUpCompactScreen()
            }

            WindowWidthSizeClass.Medium ->{
                EmailSignUpMediumScreen()
            }
        }
    }

    @Composable
    fun EmailSignUpCompactScreen(){
        Column(modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 12.dp)) {
            TintedColumn(fontSize = 16.sp, horizontalPadding = 10.dp)
        }
    }

    @Composable
    fun EmailSignUpMediumScreen(){
        Column(modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(start = 24.dp, end = 24.dp, top = 12.dp, bottom = 12.dp), verticalArrangement = Arrangement.Center) {
            TintedColumn(fontSize = 17.sp, horizontalPadding = 80.dp)
        }
    }

    @Composable
    fun TintedColumn(fontSize: TextUnit, horizontalPadding: Dp){
        Column(modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.secondaryContainer,
                shape = RoundedCornerShape(32.dp)
            )
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(horizontalPadding), horizontalAlignment = Alignment.CenterHorizontally) {

            IllustratorLogo(logo = R.drawable.logo_white_2, paddingTop = 32.dp,
                heightPercentage = .3f)

            Text("Create Account", style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold, modifier = Modifier.fillMaxHeight(.1f))

            TextFieldsColumn()

            CreateAccountButton(fontSize = fontSize)

            TermsAndConditionComposables(fontSize = fontSize)

        }
    }
    
    @Composable
    fun CreateAccountButton(fontSize: TextUnit){
        Column(modifier = Modifier
            .fillMaxHeight(.5f)
            .fillMaxWidth(), verticalArrangement = Arrangement.Center) {
            Button(onClick = { /*TODO*/ }, modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)) {
                Text(text = "Create Account", fontSize = fontSize)
            }
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
    fun TextFieldsColumn(){
        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.5f), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
            EmailTextFields()
            Spacer(modifier = Modifier.height(12.dp))
            PasswordsTextFields("Password")
            Spacer(modifier = Modifier.height(12.dp))
            PasswordsTextFields("Confirm Password")
        }
    }

    @Composable
    fun EmailTextFields(){
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
    fun PasswordsTextFields(placeHolderText: String){
        OutlinedTextField(value = "", onValueChange = {}, modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(unfocusedContainerColor = Color.White, focusedContainerColor = Color.White),
            placeholder = {
                Text(text = placeHolderText)
            }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(onNext = {
                //TODO
            }), visualTransformation = PasswordVisualTransformation(), trailingIcon = {
                Icon(painter = painterResource(id = R.drawable.baseline_visibility_24), contentDescription = "Show Password")
            }
        )
    }

    @Composable
    @Preview(showBackground = true)
    fun Preview(){
        EmailSignUpCompactScreen()
    }

    @Composable
    @Preview(showBackground = true, device = "spec:width=673dp,height=841dp")
    fun PreviewEmailSignUpMediumScreen(){
        EmailSignUpMediumScreen()
    }
