package com.victorkirui.profile.navigation

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.victorkirui.profile.ui.animalSelection.screen.AddAnimalDataOptionRoute
import com.victorkirui.profile.ui.animalSelection.screen.AnimalSelectionRoute

fun NavGraphBuilder.profileGraph(windowWidthSizeClass: WindowWidthSizeClass,
                                 navigateToAddAnimalOptions: () -> Unit,
                                 navigateToImportAnimalDataScreen: () -> Unit){
    navigation(route = ProfileRoutes.profileGraphRoute, startDestination = ProfileRoutes.animalSelectionRoute){
        composable(route = ProfileRoutes.animalSelectionRoute){
            AnimalSelectionRoute(windowWidthSizeClass, navigateToAddAnimalOptions = {
                navigateToAddAnimalOptions()
            })
        }

        composable(ProfileRoutes.addAnimalDataOptions){
            AddAnimalDataOptionRoute(windowWidthSizeClass = windowWidthSizeClass){
                navigateToImportAnimalDataScreen()
            }
        }

    }
}

fun NavController.navigateToAnimalSelectionScreen(){
    navigate(route = ProfileRoutes.animalSelectionRoute)
}

fun NavController.navigateToImportAnimalDataScreen(){
    navigate(route = ProfileRoutes.importAnimalInfo)
}

fun NavController.navigateToAddAnimalOptionsScreen(){
    navigate(route = ProfileRoutes.addAnimalDataOptions)
}

fun NavController.navigateToProfileGraph() = navigate(ProfileRoutes.profileGraphRoute)