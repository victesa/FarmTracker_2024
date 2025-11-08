package com.victorkirui.production.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.victorkirui.database.repository.ProductionRepository
import com.victorkirui.production.domain.ProductionUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductionUiViewModel @Inject constructor(
    private val productionRepository: ProductionRepository
): ViewModel() {

    private val _productionList = MutableStateFlow<List<ProductionUiState>>(emptyList())
    val productionList = _productionList.asStateFlow()

    private val _productionSubList = MutableStateFlow<List<ProductionUiState>>(emptyList())
    val productionSubList = _productionSubList.asStateFlow()

    init {
        viewModelScope.launch {
            fetchProductionDataFromDatabase()
        }
    }

    private suspend fun fetchProductionDataFromDatabase() {
        val newProductionList = mutableListOf<ProductionUiState>()

        productionRepository.getAllProductionData().collect { productionData ->
            newProductionList.addAll(
                productionData.map { produce ->
                    ProductionUiState(
                        productName = produce.productName,
                        productQuantity = "${produce.productQuantity} ${produce.productUnit}",
                        productDate = produce.date
                    )
                }
            )

            _productionList.update {
                it + newProductionList
            }

            val sublist = if (newProductionList.size > 4) newProductionList.subList(0, 3) else newProductionList

            _productionSubList.update {
                it + sublist
            }
        }
    }



}