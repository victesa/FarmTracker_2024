package com.victorkirui.database.repository

import com.victorkirui.database.entities.ProductionEntity
import kotlinx.coroutines.flow.Flow

interface ProductionRepository {

    suspend fun insertProductionData(productName: String,
                                    productQuantity: Int,
                                    productUnit: String,
                                    date: String)

    fun getAllProductionData(): Flow<List<ProductionEntity>>
}