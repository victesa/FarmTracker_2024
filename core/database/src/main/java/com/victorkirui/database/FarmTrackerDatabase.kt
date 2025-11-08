package com.victorkirui.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.victorkirui.database.daos.AnimalInfoDao
import com.victorkirui.database.daos.ExpensesDao
import com.victorkirui.database.daos.FinanceStatsDao
import com.victorkirui.database.daos.IncomeDao
import com.victorkirui.database.daos.ProductionDao
import com.victorkirui.database.entities.AnimalBasicInfoEntity
import com.victorkirui.database.entities.ExpensesEntity
import com.victorkirui.database.entities.FinanceStats
import com.victorkirui.database.entities.IncomeEntity
import com.victorkirui.database.entities.ProductionEntity

@Database(entities = [AnimalBasicInfoEntity::class,
    FinanceStats::class, ExpensesEntity::class,
    ProductionEntity::class,
    IncomeEntity::class], version = 1, exportSchema = false)

@TypeConverters(Converters::class)
abstract class FarmTrackerDatabase: RoomDatabase() {
    abstract val animalInfoDao: AnimalInfoDao
    abstract val financeStatsDao: FinanceStatsDao
    abstract val expensesDao: ExpensesDao
    abstract val productionDao: ProductionDao
    abstract val incomeDao: IncomeDao

    companion object{
        const val database_name = "farmtracker_db"
    }
}