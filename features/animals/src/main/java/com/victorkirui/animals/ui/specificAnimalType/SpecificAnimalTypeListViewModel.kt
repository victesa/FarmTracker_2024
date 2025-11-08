package com.victorkirui.animals.ui.specificAnimalType

import android.app.Application
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.victorkirui.animals.ui.entities.AnimalState
import com.victorkirui.database.repository.AnimalsRepository
import com.victorkirui.ui.getDrawable
import dagger.hilt.android.internal.Contexts.getApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class SpecificAnimalTypeListViewModel @Inject constructor(
    private val animalsRepository: AnimalsRepository
): ViewModel() {

    fun getAnimalTypeFromDatabase(currentAnimal: String): Flow<List<AnimalState>> {
        return animalsRepository.getAllAnimalsOfSpecificType(currentAnimal)
            .map { animalList ->
                animalList.map { animal ->
                    AnimalState(
                        name = animal.animalName,
                        age = animal.animalAge.toString(),
                        animalImage = animal.animalImage
                    )
                }
            }
    }


}


