package com.victorkirui.animals.ui.addingAnimals

import android.content.Context
import android.net.Uri

sealed class AddingAnimalsEvents {

    data class AnimalName(val name: String): AddingAnimalsEvents()

    data class AnimalBreed(val breed: String): AddingAnimalsEvents()

    data class AnimalGender(val gender: String): AddingAnimalsEvents()

    data class AnimalDescription(val description: String): AddingAnimalsEvents()

    data class AnimalAge(val age: String): AddingAnimalsEvents()

    data object SaveAnimal: AddingAnimalsEvents()

    data class SaveAnimalPicture(val pictureUri: Uri, val context: Context, val currentAnimal: String): AddingAnimalsEvents()

    data class DismissDialog(val currentAnimal: String): AddingAnimalsEvents()
}