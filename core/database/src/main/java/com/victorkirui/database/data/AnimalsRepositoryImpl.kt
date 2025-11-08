package com.victorkirui.database.data

import com.victorkirui.database.daos.AnimalInfoDao
import com.victorkirui.database.entities.AnimalBasicInfoEntity
import com.victorkirui.database.repository.AnimalsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AnimalsRepositoryImpl @Inject constructor(private val animalInfoDao: AnimalInfoDao): AnimalsRepository {
    override fun getAnimalTypeCount(): Flow<List<AnimalInfoDao.AnimalTypeCount>> {
        return animalInfoDao.getAnimalTypeNumbers()
    }

    override fun getAllAnimalsOfSpecificType(currentAnimal: String): Flow<List<AnimalInfoDao.SpecificAnimalTypeDetails>> {
        return animalInfoDao.getAllAnimalsOfSpecificType(currentAnimal)
    }

    override fun getAllAnimalInfo(): Flow<List<AnimalBasicInfoEntity>> {
        return animalInfoDao.getAllAnimalsInfo()
    }

    override suspend fun insertAnimalData(
        name: String,
        age: Int?,
        breed: String?,
        gender: String,
        image: String?,
        description: String?,
        currentAnimal: String
    ) {
        animalInfoDao.insert(
            AnimalBasicInfoEntity(
                name = name,
                age = age,
                breed = breed,
                gender = gender,
                animalImage = image,
                description = description,
                type = currentAnimal
            )
        )
    }

}