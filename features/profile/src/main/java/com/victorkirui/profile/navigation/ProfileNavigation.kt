package com.victorkirui.profile.navigation

import android.content.Context
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.victorkirui.profile.ui.animalSelection.AnimalSelectionRoute

const val animalSelectionRoute = "AnimalSelection"
const val profileGraphRoute = "ProfileGraphRoute"

fun NavGraphBuilder.profileGraph(context: Context){
    navigation(route = profileGraphRoute, startDestination = animalSelectionRoute){
        composable(route = animalSelectionRoute){
            AnimalSelectionRoute(context = context)
        }
    }
}

fun NavController.navigateToAnimalSelectionScreen(){
    navigate(route = animalSelectionRoute)
}

fun NavController.navigateToProfileGraph() = navigate(profileGraphRoute)