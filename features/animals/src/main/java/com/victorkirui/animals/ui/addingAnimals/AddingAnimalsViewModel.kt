package com.victorkirui.animals.ui.addingAnimals

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.victorkirui.animals.ui.entities.AddingAnimalState
import com.victorkirui.database.repository.AnimalsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class AddingAnimalsViewModel @Inject constructor(
    private val animalsRepository: AnimalsRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(AddingAnimalState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: AddingAnimalsEvents){
        when(event){
            is AddingAnimalsEvents.AnimalName ->{
                _uiState.update {
                    it.copy(
                        name = event.name
                    )
                }
            }

            is AddingAnimalsEvents.AnimalBreed ->{
                _uiState.update {
                    it.copy(
                        breed = event.breed
                    )
                }
            }

            is AddingAnimalsEvents.AnimalGender ->{
                _uiState.update {
                    it.copy(
                        gender = event.gender
                    )
                }
            }

            is AddingAnimalsEvents.AnimalDescription ->{
                _uiState.update {
                    it.copy(
                        description = event.description
                    )
                }
            }

            is AddingAnimalsEvents.AnimalAge ->{
                _uiState.update {
                    it.copy(
                        age = event.age
                    )
                }
            }

            is AddingAnimalsEvents.SaveAnimal ->{
                if (checkIfRequiredFieldsIsFilled()){
                   _uiState.update {
                       it.copy(
                           shouldShowCameraDialog = true
                       )
                   }
                }
            }

            is AddingAnimalsEvents.SaveAnimalPicture ->{
                saveAnimalDetails(event.pictureUri, context = event.context, currentAnimal = event.currentAnimal)

            }

            is  AddingAnimalsEvents.DismissDialog ->{
                _uiState.update {
                    it.copy(
                        shouldShowCameraDialog = false
                    )
                }

                viewModelScope.launch {
                    val name = uiState.value.name
                    val age = uiState.value.age.ifBlank { null }
                    val breed = uiState.value.breed.ifBlank { null }
                    val gender = uiState.value.gender
                    val description = uiState.value.description.ifBlank { null }

                    animalsRepository.insertAnimalData(
                        name = name,
                        age = age?.toInt(),
                        breed = breed,
                        gender = gender,
                        description = description,
                        currentAnimal = event.currentAnimal,
                        image = null
                    )
                }


            }
        }
    }

    private fun saveAnimalDetails(uri: Uri, context: Context?, currentAnimal: String){
        val name = uiState.value.name
        val age = uiState.value.age.ifBlank { null }
        val breed = uiState.value.breed.ifBlank { null }
        val gender = uiState.value.gender
        val description = uiState.value.description.ifBlank { null }

        viewModelScope.launch {
            storeImageToAppStorage(uri = uri, context = context!!)

            animalsRepository.insertAnimalData(
                name = name,
                age = age?.toInt(),
                breed = breed,
                gender = gender,
                description = description,
                currentAnimal = currentAnimal,
                image = uri.toString()
            )

            _uiState.update {
                it.copy(
                    navigateBackToPreviousScreen = true
                )
            }

        }
    }

    //Perform checks on the different states. If a single field is empty then it should update a state called "the first field name_isEmpty" to true
    private fun checkIfRequiredFieldsIsFilled(): Boolean{
        val name = uiState.value.name.isEmpty()
        val gender = uiState.value.gender.isEmpty()

        _uiState.update {
            it.copy(
                nameIsFilled = !name,
                genderIsFilled = !gender
            )
        }

        return !(name || gender)
    }


    fun storeImageToAppStorage(uri: Uri, context: Context){
        // Get the input stream from the content resolver
        val inputStream = context.contentResolver.openInputStream(uri)

        // Get the file extension from the content resolver
        val contentResolver: ContentResolver = context.contentResolver
        val mimeType = contentResolver.getType(uri)
        val fileExtension = mimeType?.substringAfterLast("/")

        // Generate a unique filename with the appropriate extension
        val filename = getUniqueNameFile(fileExtension ?: "jpg")

        // Create a new file in the app's internal storage directory
        val file = File(context.filesDir, filename)

        // Copy the image data from the input stream to the file
        inputStream?.use { input ->
            FileOutputStream(file).use { output ->
                input.copyTo(output)
            }
        }
    }

    private fun getUniqueNameFile(extension: String): String {
        val uniqueId = UUID.randomUUID().toString()

        return "$uniqueId.$extension"
    }

}