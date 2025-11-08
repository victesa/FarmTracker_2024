package com.victorkirui.database.di

import android.content.Context
import androidx.room.Room
import com.victorkirui.database.daos.AnimalInfoDao
import com.victorkirui.database.FarmTrackerDatabase
import com.victorkirui.database.daos.ExpensesDao
import com.victorkirui.database.daos.FinanceStatsDao
import com.victorkirui.database.daos.IncomeDao
import com.victorkirui.database.daos.ProductionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): FarmTrackerDatabase{
        return Room.databaseBuilder(
            context,
            FarmTrackerDatabase::class.java,
            FarmTrackerDatabase.database_name
        ).build()
    }
    @Provides
    @Singleton
    fun animalBasicDao(db: FarmTrackerDatabase): AnimalInfoDao {
        return db.animalInfoDao
    }

    @Provides
    @Singleton
    fun financeBasicDao(db: FarmTrackerDatabase): FinanceStatsDao{
        return db.financeStatsDao
    }

    @Provides
    @Singleton
    fun productionDao(db: FarmTrackerDatabase): ProductionDao{
        return db.productionDao
    }

    @Provides
    @Singleton
    fun incomeDao(db: FarmTrackerDatabase): IncomeDao{
        return db.incomeDao
    }

    @Provides
    @Singleton
    fun expensesDao(db: FarmTrackerDatabase): ExpensesDao{
        return db.expensesDao
    }





}