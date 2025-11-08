package com.victorkirui.animals.ui.allTypesOfAnimalsList

import android.net.Uri
import androidx.compose.runtime.mutableStateListOf
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.victorkirui.animals.ui.AnimalTypesBasicInfo
import com.victorkirui.animals.ui.entities.AnimalState
import com.victorkirui.database.repository.AnimalsRepository
import com.victorkirui.ui.getDrawable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllAnimalTypesListViewModel @Inject constructor(
    private val animalsRepository: AnimalsRepository,
    private val dataStore: DataStore<Preferences>
) : ViewModel() {

    private val _animalBasicInfo = MutableStateFlow<List<AnimalTypesBasicInfo>>(emptyList())
    val animalBasicInfo = _animalBasicInfo.asStateFlow()

    init {
        mergeDataFromDatabaseAndFireStore()
    }



    private fun mergeDataFromDatabaseAndFireStore() {
        val databaseData = getDataFromDatabase()
        val dataStoreData = getDataFromDataStore()

        val mergedData = mutableListOf<AnimalTypesBasicInfo>()

        if(databaseData.isNotEmpty()){
            val missingDataFromDataStore = databaseData.filter {database->
                !dataStoreData.any {datastore->
                    database.name == datastore.name
                }
            }

            if (missingDataFromDataStore.isNotEmpty()){
                mergedData.addAll(databaseData)
                mergedData.addAll(missingDataFromDataStore)
            }else{
                mergedData.addAll(databaseData)
            }

            _animalBasicInfo.value = mergedData
        }else{
            mergedData.addAll(dataStoreData)

            _animalBasicInfo.value = mergedData


        }
    }

    private fun getDataFromDatabase(): List<AnimalTypesBasicInfo> {
        val animalData = mutableListOf<AnimalTypesBasicInfo>()

        viewModelScope.launch{
            animalsRepository.getAnimalTypeCount().map {
                it.map {animalTypeCount ->

                    AnimalTypesBasicInfo(
                        name = animalTypeCount.animalTypeName,
                        number = animalTypeCount.count,
                        icon = getDrawable(animalTypeCount.animalTypeName)
                    )

                }
            }.collect()
        }

        return animalData
    }

    private fun getDataFromDataStore(): List<AnimalTypesBasicInfo> {
        val animalList = mutableListOf<AnimalTypesBasicInfo>()
        viewModelScope.launch {
            dataStore.data.collect { preferences ->
                val retrievedAnimalList = preferences[stringSetPreferencesKey("AnimalList")]?.toList()

                retrievedAnimalList?.let {list->
                    animalList.addAll(
                        list.map {
                            AnimalTypesBasicInfo(
                                name = it,
                                number = 0,
                                icon = getDrawable(it)
                            )
                        }
                    )
                }
            }
        }

        return animalList
    }
}
