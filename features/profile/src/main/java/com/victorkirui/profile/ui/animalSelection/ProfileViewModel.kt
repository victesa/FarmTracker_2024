package com.victorkirui.profile.ui.animalSelection

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.victorkirui.profile.data.AnimalNames
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val dataStore: DataStore<Preferences>): ViewModel() {

    val listOfAnimalsImageUrl = listOf(
        "https://images.unsplash.com/photo-1589526261866-ab0d34f8dc19?crop=entropy&cs=srgb&fm=jpg&ixid=M3w0Mzc0NDd8MHwxfHNlYXJjaHw0fHxiZWV8ZW58MHx8fHwxNzA2MjUyNTMyfDA&ixlib=rb-4.0.3&q=85&q=85&fmt=jpg&crop=entropy&cs=tinysrgb&w=450",
        "https://images.unsplash.com/photo-1548550023-2bdb3c5beed7?crop=entropy&cs=srgb&fm=jpg&ixid=M3w0Mzc0NDd8MHwxfHNlYXJjaHwzfHxjaGlja2VufGVufDB8fHx8MTcwNjI3NzcxOXww&ixlib=rb-4.0.3&q=85&q=85&fmt=jpg&crop=entropy&cs=tinysrgb&w=450",
        "https://images.unsplash.com/photo-1527153857715-3908f2bae5e8?crop=entropy&cs=srgb&fm=jpg&ixid=M3w0Mzc0NDd8MHwxfHNlYXJjaHwzfHxjb3d8ZW58MHx8fHwxNzA2Mjc4OTc1fDA&ixlib=rb-4.0.3&q=85&q=85&fmt=jpg&crop=entropy&cs=tinysrgb&w=450",
        "https://images.unsplash.com/photo-1602081114407-99c109e945c4?crop=entropy&cs=srgb&fm=jpg&ixid=M3w0Mzc0NDd8MHwxfHNlYXJjaHw2fHxkb25rZXl8ZW58MHx8fHwxNzA2Mjk5NDk4fDA&ixlib=rb-4.0.3&q=85&q=85&fmt=jpg&crop=entropy&cs=tinysrgb&w=450",
        "https://images.unsplash.com/photo-1566301587696-4a1a319c2cd3?crop=entropy&cs=srgb&fm=jpg&ixid=M3w0Mzc0NDd8MHwxfHNlYXJjaHw5fHxkdWNrfGVufDB8MXx8fDE3MDYyOTk1MzV8Mg&ixlib=rb-4.0.3&q=85&q=85&fmt=jpg&crop=entropy&cs=tinysrgb&w=450",
        "https://images.unsplash.com/photo-1690527626754-024082731b47?crop=entropy&cs=srgb&fm=jpg&ixid=M3w0Mzc0NDd8MHwxfHNlYXJjaHwyMnx8Z29hdHxlbnwwfDJ8fHwxNzA2Mjk5NTgzfDI&ixlib=rb-4.0.3&q=85&q=85&fmt=jpg&crop=entropy&cs=tinysrgb&w=450",
        "https://images.unsplash.com/photo-1610319308223-321c524e8f8e?crop=entropy&cs=srgb&fm=jpg&ixid=M3w0Mzc0NDd8MHwxfHNlYXJjaHwxMnx8aG9yc2V8ZW58MHwyfHx8MTcwNjI5OTYxNHwy&ixlib=rb-4.0.3&q=85&q=85&fmt=jpg&crop=entropy&cs=tinysrgb&w=450",
        "https://images.unsplash.com/photo-1630173209017-9d64df630aab?crop=entropy&cs=srgb&fm=jpg&ixid=M3w0Mzc0NDd8MHwxfHNlYXJjaHw0fHxwaWd8ZW58MHwyfHx8MTcwNjI5OTY2MXwy&ixlib=rb-4.0.3&q=85&q=85&fmt=jpg&crop=entropy&cs=tinysrgb&w=450",
        "https://images.unsplash.com/photo-1584407348627-6be4658b8951?crop=entropy&cs=srgb&fm=jpg&ixid=M3w0Mzc0NDd8MHwxfHNlYXJjaHwyfHxxdWFpbHxlbnwwfDJ8fHwxNzA2Mjk5Njc3fDI&ixlib=rb-4.0.3&q=85&q=85&fmt=jpg&crop=entropy&cs=tinysrgb&w=450",
        "https://images.unsplash.com/photo-1589933767411-38a58367efd7?crop=entropy&cs=srgb&fm=jpg&ixid=M3w0Mzc0NDd8MHwxfHNlYXJjaHwxfHxyYWJiaXR8ZW58MHwyfHx8MTcwNjI5OTY5Mnwy&ixlib=rb-4.0.3&q=85&q=85&fmt=jpg&crop=entropy&cs=tinysrgb&w=450",
        "https://images.unsplash.com/photo-1557735065-ebd5a235f490?crop=entropy&cs=srgb&fm=jpg&ixid=M3w0Mzc0NDd8MHwxfHNlYXJjaHw0fHxzaGVlcHxlbnwwfDJ8fHwxNzA2Mjk5NzEzfDI&ixlib=rb-4.0.3&q=85&q=85&fmt=jpg&crop=entropy&cs=tinysrgb&w=450",
        "https://images.unsplash.com/photo-1583147386746-3ba0c0799d79?crop=entropy&cs=srgb&fm=jpg&ixid=M3w0Mzc0NDd8MHwxfHNlYXJjaHw1fHx0dXJrZXklMjBiaXJkfGVufDB8Mnx8fDE3MDYyOTk3NDZ8Mg&ixlib=rb-4.0.3&q=85&q=85&fmt=jpg&crop=entropy&cs=tinysrgb&w=450"
    )

    //A list of the animals Names
    val animalNamesList = AnimalNames.values().map {
        it.name
    }


    private val _uiState = MutableStateFlow(animalNamesList.map {
        AnimalState(name = it)
    })

    val uiState: StateFlow<List<AnimalState>> = _uiState.asStateFlow()

    private val animalSelected = mutableListOf<String>()

    fun animalClicked(id: Int){
        if (!uiState.value[id].isSelected){
            animalSelected.add(uiState.value[id].name)
        }else{
            animalSelected.remove(uiState.value[id].name)
        }
        _uiState.update {
            it.mapIndexed{j, item->
                if (j == id){
                    item.copy(
                        isSelected = !uiState.value[id].isSelected
                    )}else{
                        item
                    }
                }

            }
    }

    var isAnimalSelected by mutableStateOf(true)


    fun onNextClicked(): Boolean{
        return if (animalSelected.isNotEmpty()){
            isAnimalSelected = true
            viewModelScope.launch {
                dataStore.edit {
                    it[stringSetPreferencesKey("AnimalList")] = animalSelected.toSet()
                }

                val retrievedAnimalList = dataStore.data.first()[stringSetPreferencesKey("AnimalList")]

                // Log the retrieved list of animals
                Log.d("RetrievedAnimalList", retrievedAnimalList.toString())
            }
            Log.d("AnimalList", animalSelected[0])


            true
        }else{
            isAnimalSelected = false
            false
        }
    }




}