package com.victorkirui.authentication.navigation

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.victorkirui.authentication.ui.emailSignIn.EmailSignInRoute
import com.victorkirui.authentication.ui.emailSignUp.EmailSignUpRoute
import com.victorkirui.authentication.ui.OnBoardingRoute
import com.victorkirui.authentication.ui.PhoneAuthenticationRoute
import com.victorkirui.authentication.ui.SignUpOptionsRoute

fun NavGraphBuilder.authenticationGraph(windowWidthSizeClass: WindowWidthSizeClass,
                                        onSignUpClicked:()-> Unit, onSignInClicked:()-> Unit,
                                        navigateToEmailSignUpScreen:() -> Unit,
                                        navigateToPhoneAuthenticationScreen:() -> Unit,
                                        navigateToEmailSignInScreen:() -> Unit,
                                        navigateToProfileGraph:() -> Unit,
                                        navigateToHomeScreen:() -> Unit){
    navigation(route = AuthenticationRoutes.authenticationGraph,
        startDestination = AuthenticationRoutes.onBoardingRoute){
        composable(route = AuthenticationRoutes.onBoardingRoute){
            OnBoardingRoute(onSignInClicked = onSignInClicked,
                onSignUpClicked = onSignUpClicked,
                windowWidthSizeClass = windowWidthSizeClass)
        }

        composable(route = AuthenticationRoutes.signUpOptions){
            SignUpOptionsRoute(
                navigateToPhoneAuthenticationScreen = navigateToPhoneAuthenticationScreen,
                navigateToEmailSignUpScreen = navigateToEmailSignUpScreen,
                navigateToEmailSignInScreen = navigateToEmailSignInScreen,
                windowWidthSizeClass = windowWidthSizeClass)
        }

        composable(route = AuthenticationRoutes.emailSignInRoute){
            EmailSignInRoute(navigateToPhoneAuthenticationScreen = navigateToPhoneAuthenticationScreen,
                navigateToSignUpEmail = navigateToEmailSignUpScreen,
                windowWidthSizeClass = windowWidthSizeClass,
                navigateToHomeScreen = {
                    navigateToHomeScreen()
                }
            )
        }

        composable(route = AuthenticationRoutes.emailSignUpRoute){
            EmailSignUpRoute(navigateToProfileGraph = navigateToProfileGraph,
                windowWidthSizeClass = windowWidthSizeClass)
        }

        composable(route = AuthenticationRoutes.phoneAuthenticationRoute){
            PhoneAuthenticationRoute(windowWidthSizeClass = windowWidthSizeClass)
        }
    }
}

fun NavController.navigateToSignUpOptionsScreen(){
    navigate(AuthenticationRoutes.signUpOptions)
    saveState()
}

fun NavController.navigateToEmailSignInScreen(){
    navigate(AuthenticationRoutes.emailSignInRoute)
}

fun NavController.navigateToEmailSignUpScreen(){
    navigate(AuthenticationRoutes.emailSignUpRoute)
}

fun NavController.navigateToPhoneAuthenticationScreen(){
    navigate(AuthenticationRoutes.phoneAuthenticationRoute)
}