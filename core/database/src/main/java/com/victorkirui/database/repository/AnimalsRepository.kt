package com.victorkirui.database.repository

import com.victorkirui.database.daos.AnimalInfoDao
import com.victorkirui.database.entities.AnimalBasicInfoEntity
import kotlinx.coroutines.flow.Flow

interface AnimalsRepository {

    fun getAnimalTypeCount(): Flow<List<AnimalInfoDao.AnimalTypeCount>>

    fun getAllAnimalsOfSpecificType(currentAnimal: String): Flow<List<AnimalInfoDao.SpecificAnimalTypeDetails>>

    fun getAllAnimalInfo(): Flow<List<AnimalBasicInfoEntity>>

    suspend fun insertAnimalData(name: String, age: Int?,
                                 breed: String?, gender: String,
                                 image: String?, description: String?,
                                 currentAnimal: String)
}