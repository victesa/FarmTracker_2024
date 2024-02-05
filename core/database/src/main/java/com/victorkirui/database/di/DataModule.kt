package com.victorkirui.database.di

import android.app.Activity
import com.victorkirui.database.data.AnimalBasicInfoRepositoryImpl
import com.victorkirui.database.repository.AnimalBasicInfoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindAnimalBasicRepo(animalBasicInfoRepositoryImpl: AnimalBasicInfoRepositoryImpl): AnimalBasicInfoRepository
}