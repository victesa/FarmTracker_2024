package com.victorkirui.farmtracker.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.victorkirui.authentication.navigation.authenticationGraph
import com.victorkirui.authentication.navigation.navigateToEmailSignInScreen
import com.victorkirui.authentication.navigation.navigateToEmailSignUpScreen
import com.victorkirui.authentication.navigation.navigateToPhoneAuthenticationScreen
import com.victorkirui.authentication.navigation.navigateToSignUpOptionsScreen
import com.victorkirui.authentication.navigation.onBoardingRoute
import com.victorkirui.profile.navigation.navigateToProfileGraph
import com.victorkirui.profile.navigation.profileGraph

@Composable
fun AppNav(context: Context){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "auth" ){

        authenticationGraph(context = context,
            onSignUpClicked = navController::navigateToSignUpOptionsScreen,
            onSignInClicked = navController::navigateToEmailSignInScreen,
            navigateToEmailSignInScreen = navController::navigateToEmailSignInScreen,
            navigateToEmailSignUpScreen = navController::navigateToEmailSignUpScreen,
            navigateToPhoneAuthenticationScreen = navController::navigateToPhoneAuthenticationScreen,
            navigateToProfileGraph = navController::navigateToProfileGraph)

        profileGraph(context)
    }
}