package com.victorkirui.home.domain.usecase

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.victorkirui.database.repository.HomeRepository
import kotlinx.coroutines.flow.Flow


class AnimalSummary(private val homeRepository: HomeRepository,
    private val dataStore: DataStore<Preferences>) {


}