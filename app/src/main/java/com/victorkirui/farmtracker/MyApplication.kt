package com.victorkirui.farmtracker

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.victorkirui.animals.ui.allTypesOfAnimalsList.AllAnimalTypesListViewModel
import com.victorkirui.animals.ui.specificAnimalType.SpecificAnimalTypeListViewModel
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = "MyDataStore"
)

@HiltAndroidApp
class MyApplication: Application() {
}

