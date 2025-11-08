package com.victorkirui.animals.ui.entities

data class AddingAnimalState(
    val name: String = "",
    val age: String = "",
    val breed: String = "",
    val gender: String = "",
    val description: String = "",
    val shouldShowCameraDialog: Boolean = false,
    val nameIsFilled: Boolean = true,
    val genderIsFilled: Boolean = true,
    val navigateBackToPreviousScreen: Boolean = false
)
