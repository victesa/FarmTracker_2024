package com.victorkirui.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.victorkirui.database.entities.ProductionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductionDao {

    @Insert
    fun insertData(productionEntity: ProductionEntity)

    @Query("SELECT * FROM production_entity ORDER BY date DESC")
    fun getAllProductionData(): Flow<List<ProductionEntity>>
}