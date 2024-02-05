package com.victorkirui.farmtracker.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import java.io.IOException

class AuthenticationPreferencesRepository(
    private val dataStore: DataStore<Preferences>
) {

    private companion object{
        val authenticationPreferencesKey = booleanPreferencesKey("authentication_state")
    }

    suspend fun readAuthStatePreferences(): Boolean?{
        return dataStore.data
            .catch {
                if (it is IOException){
                    emit(emptyPreferences())
                }else{
                    throw it
                }
            }
            .first()[authenticationPreferencesKey] ?: false

    }

    suspend fun writeAuthStatePreferences(isLoggedIn: Boolean){
        dataStore.edit {
            it[authenticationPreferencesKey] = isLoggedIn
        }
    }

}