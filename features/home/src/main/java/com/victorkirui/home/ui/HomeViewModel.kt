package com.victorkirui.home.ui

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.victorkirui.database.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
    private val dataStore: DataStore<Preferences>
) : ViewModel() {

    private val _selectedPeriod = MutableStateFlow("Daily")
    val selectedPeriod: StateFlow<String> = _selectedPeriod.asStateFlow()

    private val _financeState = MutableStateFlow(FinanceState())
    val financeState: StateFlow<FinanceState> = _financeState.asStateFlow()

    private val _animalList = MutableStateFlow<List<AnimalState>>(emptyList())
    val animalList: StateFlow<List<AnimalState>> = _animalList.asStateFlow()


    private val financeStateFlow: Flow<FinanceState> = combine(
        homeRepository.getTotalIncomeForTheDay(getTodayDate()),
        homeRepository.getTotalIncomeForTheWeek(getStartOfTheWeekDate(), getTodayDate()),
        homeRepository.getTotalIncomeForTheMonth(getStartOfTheMonthDate(), getTodayDate()),
        homeRepository.getTotalExpensesForTheDay(getTodayDate()),
        homeRepository.getTotalExpensesForTheWeek(getStartOfTheWeekDate(), getTodayDate()),
        homeRepository.getTotalExpensesForTheMonth(getStartOfTheMonthDate(), getTodayDate()),
        homeRepository.getTotalProfitForTheDay(getTodayDate()),
        homeRepository.getTotalProfitForTheWeek(getStartOfTheWeekDate(), getTodayDate()),
        homeRepository.getTotalProfitForTheMonth(getStartOfTheMonthDate(), getTodayDate()),
        homeRepository.getNumberOfColumns()
    ) {
        FinanceState(
            incomeDaily = it[0],
            incomeWeekly = it[1],
            incomeMonthly = it[2],
            expensesDaily = it[3],
            expensesWeekly = it[4],
            expensesMonthly = it[5],
            profitDaily = it[6],
            profitWeekly = it[7],
            profitMonthly = it[8],
            showFinanceEmptyMessage = it[9] == 0
        )
    }

    private fun initializeFinanceData(){
        viewModelScope.launch {
            financeStateFlow.collect{
                _financeState.value = it
            }
        }
    }

    init {
        initializeFinanceData()
        getAnimalDataFromDataStoreAndDatabase()
    }


    private fun getAnimalDataFromDataStoreAndDatabase() {
        viewModelScope.launch {
            val animalDataFromDatabase = homeRepository.getNumberOfAnimals().first()

            // Collect animal data from DataStore
            dataStore.data.collect { preferences ->
                val retrievedAnimalList = preferences[stringSetPreferencesKey("AnimalList")]?.toList()
                retrievedAnimalList?.let {
                    val animalNewList = mutableListOf<AnimalState>()
                    for (i in it){
                        animalNewList.add(AnimalState(i, 0, getDrawable(i)))
                    }
                    // Update the UI state with data from DataStore only if the database is empty
                    if (animalDataFromDatabase.isEmpty()) {
                        _animalList.value = animalNewList
                        Log.d("DataStoreAnimals", _animalList.value.toString())
                    }else{
                        _animalList.value = animalDataFromDatabase.map { AnimalState(it.animalType, it.count, getDrawable(it.animalType)) }
                        Log.d("MyAnimals", _animalList.value.toString())
                    }
                }
            }
        }
    }


    private fun getTodayDate(): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return LocalDate.now().format(formatter)
    }

    private fun getStartOfTheWeekDate(): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)).format(formatter)
    }

    private fun getStartOfTheMonthDate(): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return LocalDate.now().with(TemporalAdjusters.firstDayOfMonth() ).format(formatter)
    }

    private fun getDrawable(animalName: String): Int{
        return when(animalName){
            "Cow" ->{
                com.victorkirui.ui.R.drawable.cow
            }

            "Goat" ->{
                com.victorkirui.ui.R.drawable.goat
            }

            "Bees" ->{
                com.victorkirui.ui.R.drawable.bees
            }

            "Chicken" ->{
                com.victorkirui.ui.R.drawable.chicken
            }

            "Donkey" ->{
                com.victorkirui.ui.R.drawable.donkey
            }

            "Goose" ->{
                com.victorkirui.ui.R.drawable.goose
            }

            "Horse" ->{
                com.victorkirui.ui.R.drawable.horse
            }

            "Pig" ->{
                com.victorkirui.ui.R.drawable.pig
            }

            "Quail" ->{
                com.victorkirui.ui.R.drawable.quail
            }

            "Rabbits" ->{
                com.victorkirui.ui.R.drawable.rabbits
            }

            "Sheep" ->{
                com.victorkirui.ui.R.drawable.sheep
            }

            "Turkey" ->{
                com.victorkirui.ui.R.drawable.turkey
            }

            else -> {
                com.victorkirui.ui.R.drawable.cow
            }
        }
    }

    fun onPeriodClicked(period: String) {
        _selectedPeriod.value = period
        viewModelScope.launch {
            val retrievedAnimalList = dataStore.data.first()[stringSetPreferencesKey("AnimalList")]

            // Log the retrieved list of animals
            Log.d("RetrievedAnimalList", retrievedAnimalList.toString())
        }
    }

}
