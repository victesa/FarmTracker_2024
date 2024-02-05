package com.victorkirui.database.data

import com.victorkirui.database.AnimalCountPerType
import com.victorkirui.database.AnimalInfoDao
import com.victorkirui.database.AnimalInfoEntity
import com.victorkirui.database.repository.AnimalBasicInfoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AnimalBasicInfoRepositoryImpl @Inject constructor(private val animalInfoDao: AnimalInfoDao): AnimalBasicInfoRepository {
    override fun getAllData(): Flow<List<AnimalInfoEntity>> {
        return animalInfoDao.allAnimalData()
    }

    override fun getNumberOfAnimal(): Flow<List<AnimalCountPerType>> {
        return animalInfoDao.getNumberOfAnimalsPerType()
    }

    override suspend fun insertAnimalBasicData(animalInfoEntity: AnimalInfoEntity) {
        animalInfoDao.insert(animalInfoEntity)
    }
}