package com.victorkirui.database.data

import com.victorkirui.database.daos.ProductionDao
import com.victorkirui.database.entities.ProductionEntity
import com.victorkirui.database.repository.ProductionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductionRepositoryImpl @Inject constructor(
    private val productionDao: ProductionDao): ProductionRepository {
    override suspend fun insertProductionData(productName: String,
                                             productQuantity: Int,
                                             productUnit: String,
                                             date: String) {
        val productionEntity = ProductionEntity(
            productName = productName,
            productQuantity = productQuantity,
            productUnit = productUnit,
            date = date
        )

        productionDao.insertData(productionEntity)
    }

    override fun getAllProductionData(): Flow<List<ProductionEntity>> {
        return productionDao.getAllProductionData()
    }
}