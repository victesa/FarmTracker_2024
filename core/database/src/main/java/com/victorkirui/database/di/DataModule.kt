package com.victorkirui.database.di

import com.victorkirui.database.data.AnimalsRepositoryImpl
import com.victorkirui.database.data.FinanceRepositoryImpl
import com.victorkirui.database.data.HomeRepositoryImpl
import com.victorkirui.database.data.ProductionRepositoryImpl
import com.victorkirui.database.repository.AnimalsRepository
import com.victorkirui.database.repository.FinanceRepository
import com.victorkirui.database.repository.HomeRepository
import com.victorkirui.database.repository.ProductionRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindHomeRepo(homeRepositoryImpl: HomeRepositoryImpl): HomeRepository

    @Binds
    abstract fun bindAnimalRepo(animalsRepositoryImpl: AnimalsRepositoryImpl): AnimalsRepository

    @Binds
    abstract fun bindProductionRepo(productionRepositoryImpl: ProductionRepositoryImpl): ProductionRepository

    @Binds
    abstract fun bindFinanceRepo(financeRepositoryImpl: FinanceRepositoryImpl): FinanceRepository

}