package com.victorkirui.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.victorkirui.database.AnimalCountPerType
import com.victorkirui.database.data.AnimalBasicInfoRepositoryImpl
import com.victorkirui.database.data.Car
import com.victorkirui.database.repository.AnimalBasicInfoRepository
import com.victorkirui.home.data.Test
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
     private val car: Car
) : ViewModel() {

    private val _selectedPeriod = MutableStateFlow("Daily")
    val selectedPeriod: StateFlow<String> = _selectedPeriod.asStateFlow()

    private val _financeState = MutableStateFlow(FinanceState())
    val financeState: StateFlow<FinanceState> = _financeState.asStateFlow()

    private val _animalBasicData = MutableStateFlow<List<AnimalCountPerType>>(emptyList())
    val animalBasicData: StateFlow<List<AnimalCountPerType>> = _animalBasicData.asStateFlow()

    private val _filteredAnimalList = MutableStateFlow<List<AnimalCountPerType>>(emptyList())
    val filteredAnimalList = _filteredAnimalList.asStateFlow()

    init {
        viewModelScope.launch {
            //loadAnimalBasicData()
        }
    }

//    private suspend fun loadAnimalBasicData() {
//        try {
//            animalBasicInfoRepository.getNumberOfAnimal().collect { newList ->
//                _animalBasicData.value = newList
//                updateFilteredAnimalList()
//            }
//        } catch (e: Exception) {
//            // Handle error, maybe set a state indicating an error
//            e.printStackTrace()
//        }
//    }

    private fun updateFilteredAnimalList() {
        val originalList = animalBasicData.value
        _filteredAnimalList.value = if (originalList.size > 4) {
            originalList.subList(0, 4)
        } else {
            originalList
        }
    }

    fun onPeriodClicked(period: String) {
        _selectedPeriod.value = period
    }
}
