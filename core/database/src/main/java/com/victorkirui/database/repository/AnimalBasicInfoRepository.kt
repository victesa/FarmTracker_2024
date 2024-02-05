package com.victorkirui.database.repository

import android.content.ClipData.Item
import com.victorkirui.database.AnimalCountPerType
import com.victorkirui.database.AnimalInfoEntity
import kotlinx.coroutines.flow.Flow

interface AnimalBasicInfoRepository {

    fun getAllData(): Flow<List<AnimalInfoEntity>>

    fun getNumberOfAnimal(): Flow<List<AnimalCountPerType>>

    suspend fun insertAnimalBasicData(animalInfoEntity: AnimalInfoEntity)
}