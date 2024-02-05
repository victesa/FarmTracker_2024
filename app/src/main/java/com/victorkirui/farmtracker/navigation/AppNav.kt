package com.victorkirui.farmtracker.navigation

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.victorkirui.authentication.navigation.authenticationGraph
import com.victorkirui.authentication.navigation.navigateToEmailSignInScreen
import com.victorkirui.authentication.navigation.navigateToEmailSignUpScreen
import com.victorkirui.authentication.navigation.navigateToPhoneAuthenticationScreen
import com.victorkirui.authentication.navigation.navigateToSignUpOptionsScreen
import com.victorkirui.profile.navigation.navigateToAddAnimalOptionsScreen
import com.victorkirui.profile.navigation.navigateToImportAnimalDataScreen
import com.victorkirui.profile.navigation.navigateToProfileGraph
import com.victorkirui.profile.navigation.profileGraph

@Composable
fun AppNav(windowWidthSizeClass: WindowWidthSizeClass, startScreenRoute: String){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startScreenRoute ){

        authenticationGraph(windowWidthSizeClass,
            onSignUpClicked = navController::navigateToSignUpOptionsScreen,
            onSignInClicked = navController::navigateToEmailSignInScreen,
            navigateToEmailSignInScreen = navController::navigateToEmailSignInScreen,
            navigateToEmailSignUpScreen = navController::navigateToEmailSignUpScreen,
            navigateToPhoneAuthenticationScreen = navController::navigateToPhoneAuthenticationScreen,
            navigateToProfileGraph = {
                navController.navigateToProfileGraph()

            })

        profileGraph(windowWidthSizeClass = windowWidthSizeClass, navigateToAddAnimalOptions = {
            navController.navigateToAddAnimalOptionsScreen()
        },
            navigateToImportAnimalDataScreen = {
                navController.navigateToImportAnimalDataScreen()
            })
    }
}