package com.victorkirui.database.di

import android.app.Application
import androidx.room.Room
import com.victorkirui.database.AnimalInfoDao
import com.victorkirui.database.FarmTrackerDatabase
import com.victorkirui.database.data.AnimalBasicInfoRepositoryImpl
import com.victorkirui.database.data.Car
import com.victorkirui.database.repository.AnimalBasicInfoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): FarmTrackerDatabase{
        return Room.databaseBuilder(
            app,
            FarmTrackerDatabase::class.java,
            FarmTrackerDatabase.database_name
        ).build()
    }
    @Provides
    @Singleton
    fun animalBasicDao(db: FarmTrackerDatabase): AnimalInfoDao{
        return db.animalInfoDao
    }

    @Provides
    @Singleton
    fun provideAnimalBasicRepoImplementation(animalDao: AnimalInfoDao): AnimalBasicInfoRepositoryImpl{
        return AnimalBasicInfoRepositoryImpl(animalDao)
    }

    @Provides
    @Singleton
    fun provideCar(): Car{
        return Car()
    }


}