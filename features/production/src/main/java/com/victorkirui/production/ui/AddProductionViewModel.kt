package com.victorkirui.production.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.victorkirui.database.repository.ProductionRepository
import com.victorkirui.production.domain.AddProductionEvents
import com.victorkirui.production.domain.AddProductionState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddProductionViewModel @Inject constructor(
    private val productionRepository: ProductionRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(AddProductionState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(events: AddProductionEvents){
        when(events){
            is AddProductionEvents.ProductName ->{
                _uiState.update {
                    it.copy(
                        productName = events.product
                    )
                }
            }

            is AddProductionEvents.ProductQuantity ->{
                _uiState.update {
                    it.copy(
                        productQuantity = events.amount
                    )
                }
            }

            is AddProductionEvents.ProductUnit ->{
                _uiState.update {
                    it.copy(
                        productUnit = events.unit,
                        shouldShowDropDownMenu = false
                    )
                }
            }

            is AddProductionEvents.ProductDate ->{
                _uiState.update {
                    it.copy(
                        date = events.date
                    )
                }
            }

            is AddProductionEvents.DismissDropDownMenu ->{
                _uiState.update {
                    it.copy(
                        shouldShowDropDownMenu = false
                    )
                }
            }

            is AddProductionEvents.OnExpandedChanged ->{
                _uiState.update {
                    it.copy(
                        shouldShowDropDownMenu = !uiState.value.shouldShowDropDownMenu
                    )
                }
            }

            is AddProductionEvents.DismissDatePickerDialog ->{
                _uiState.update {
                    it.copy(
                        shouldShowDatePickerDialog = false
                    )
                }
            }

            is AddProductionEvents.ShowDatePickerDialog ->{
                _uiState.update {
                    it.copy(
                        shouldShowDatePickerDialog = true
                    )
                }
            }

            is AddProductionEvents.saveProduction ->{
                saveProductionData()
            }
        }
    }

    private fun saveProductionData(){
        val productName = uiState.value.productName.isBlank()
        val productQuantity = uiState.value.productQuantity.isBlank()
        if (productName || productQuantity){
            _uiState.update {
                it.copy(
                    productNameTextFieldError = productName,
                    productAmountTextFieldError = productQuantity
                )
            }

        }else{
            viewModelScope.launch {
                productionRepository.insertProductionData(
                    productName = uiState.value.productName,
                    productQuantity = uiState.value.productQuantity.toInt(),
                    productUnit = uiState.value.productUnit,
                    date = uiState.toString()
                )
            }
        }
    }
}