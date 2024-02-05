package com.victorkirui.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.victorkirui.database.AnimalInfoDao

@Database(entities = [AnimalInfoEntity::class], version = 1, exportSchema = false)
abstract class FarmTrackerDatabase: RoomDatabase() {
    abstract val animalInfoDao: AnimalInfoDao

    companion object{
        const val database_name = "farmtracker_db"
    }
}