package com.victorkirui.database

import android.content.ClipData.Item
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

data class AnimalCountPerType(
    val animalType: String,
    val numberOfAnimals: Int
)

@Dao
interface AnimalInfoDao {

    @Query("SELECT * FROM AnimalInfoEntity")
    fun allAnimalData(): Flow<List<AnimalInfoEntity>>

    @Query("SELECT animal_type AS animalType, COUNT(animal_name) AS numberOfAnimals \n" +
            "FROM animalinfoentity\n" +
            "GROUP BY animal_type")
    fun getNumberOfAnimalsPerType(): Flow<List<AnimalCountPerType>>

    @Insert
    suspend fun insert(animalInfoEntity: AnimalInfoEntity)
}