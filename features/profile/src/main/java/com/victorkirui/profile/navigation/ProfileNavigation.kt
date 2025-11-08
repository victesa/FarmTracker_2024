package com.victorkirui.profile.navigation

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.victorkirui.profile.ui.animalSelection.screen.AnimalSelectionRoute

fun NavGraphBuilder.profileGraph(windowWidthSizeClass: WindowWidthSizeClass,
                                 navigateToHomeScreen:()-> Unit){
    navigation(route = ProfileRoutes.profileGraphRoute, startDestination = ProfileRoutes.animalSelectionRoute){
        composable(route = ProfileRoutes.animalSelectionRoute){
            AnimalSelectionRoute(windowWidthSizeClass, navigateToHomeScreen = navigateToHomeScreen)
        }

    }
}

fun NavController.navigateToProfileGraph() = navigate(ProfileRoutes.profileGraphRoute)