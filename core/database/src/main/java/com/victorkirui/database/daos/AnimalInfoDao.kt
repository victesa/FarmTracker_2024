package com.victorkirui.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.victorkirui.database.entities.AnimalBasicInfoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimalInfoDao {

    @Insert
    suspend fun insert(animalBasicInfoEntity: AnimalBasicInfoEntity)

    @Delete
    suspend fun delete(animalBasicInfoEntity: AnimalBasicInfoEntity)

    @Query("SELECT * FROM animalbasicinfoentity")
    fun getAllAnimalBasicInfo(): Flow<List<AnimalBasicInfoEntity>>

    @Query("SELECT animal_type as animalType, COUNT(*) as count FROM animalbasicinfoentity GROUP BY animalType")
    fun getNumberOfAnimals(): Flow<List<AnimalNameCount>>

    @Query("SELECT animal_type as animalTypeName, COUNT(*) as count FROM animalbasicinfoentity GROUP BY animal_type")
    fun getAnimalTypeNumbers(): Flow<List<AnimalTypeCount>>

    @Query("SELECT animal_name as animalName, age as animalAge, animal_image as animalImage FROM animalbasicinfoentity WHERE animal_type == :animalType")
    fun getAllAnimalsOfSpecificType(animalType: String): Flow<List<SpecificAnimalTypeDetails>>

    @Query("SELECT * FROM animalbasicinfoentity")
    fun getAllAnimalsInfo(): Flow<List<AnimalBasicInfoEntity>>

    // Define a data class to represent the result of the query
    data class AnimalNameCount(
        val animalType: String,
        val count: Int
    )

    data class AnimalTypeCount(
        val animalTypeName: String,
        val count: Int
    )

    data class SpecificAnimalTypeDetails(
        val animalName: String,
        val animalAge: Int?,
        val animalImage: String?
    )
}
