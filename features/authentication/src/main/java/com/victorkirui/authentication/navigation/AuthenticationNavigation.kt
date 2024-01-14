package com.victorkirui.authentication.navigation

import android.content.Context
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.victorkirui.authentication.ui.EmailSignInRoute
import com.victorkirui.authentication.ui.EmailSignUpRoute
import com.victorkirui.authentication.ui.OnBoardingRoute
import com.victorkirui.authentication.ui.PhoneAuthenticationRoute
import com.victorkirui.authentication.ui.SignUpOptionsRoute

const val onBoardingRoute = "OnBoarding"
const val signUpOptions = "SignUpOptions"
const val emailSignInRoute = "EmailSignInScreen"
const val emailSignUpRoute = "EmailSignUpScreen"
const val phoneAuthenticationRoute = "PhoneAuthenticationScreen"

fun NavGraphBuilder.authenticationGraph(context: Context,
                                        onSignUpClicked:()-> Unit, onSignInClicked:()-> Unit,
                                        navigateToEmailSignUpScreen:() -> Unit,
                                        popBackStack:() -> Unit,
                                        navigateToPhoneAuthenticationScreen:() -> Unit,
                                        navigateToEmailSignInScreen:() -> Unit){
    navigation(route = "auth", startDestination = onBoardingRoute){
        composable(route = onBoardingRoute){
            OnBoardingRoute(context = context, onSignInClicked = onSignInClicked, onSignUpClicked = onSignUpClicked)
        }

        composable(route = signUpOptions){
            SignUpOptionsRoute(context = context,
                navigateToPhoneAuthenticationScreen = navigateToPhoneAuthenticationScreen,
                navigateToEmailSignUpScreen = navigateToEmailSignUpScreen,
                navigateToEmailSignInScreen = navigateToEmailSignInScreen)
        }
        
        composable(route = emailSignInRoute){
            EmailSignInRoute(context = context, navigateToPhoneAuthenticationScreen = navigateToPhoneAuthenticationScreen, navigateToSignUpEmail = navigateToEmailSignUpScreen
            )
        }
        
        composable(route = emailSignUpRoute){
            EmailSignUpRoute(context = context)
        }
        
        composable(route = phoneAuthenticationRoute){
            PhoneAuthenticationRoute(context = context)
        }
    }
}

fun NavController.navigateToSignUpOptionsScreen(){
    navigate(signUpOptions)
    saveState()
}

fun NavController.navigateToEmailSignInScreen(){
    navigate(emailSignInRoute)
}

fun NavController.navigateToEmailSignUpScreen(){
    navigate(emailSignUpRoute)
}

fun NavController.navigateToPhoneAuthenticationScreen(){
    navigate(phoneAuthenticationRoute)
}