package com.victorkirui.farmtracker.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.victorkirui.farmtracker.data.AuthenticationPreferencesRepository
import com.victorkirui.farmtracker.dataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun dataStoreInstance(@ApplicationContext context: Context): DataStore<Preferences>{
        return context.dataStore
    }

    @Provides
    @Singleton
    fun getAuthenticationPreferencesRepository(dataStore: DataStore<Preferences>): AuthenticationPreferencesRepository{
        return AuthenticationPreferencesRepository(dataStore)
    }
}