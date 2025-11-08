package com.victorkirui.finance.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.victorkirui.database.entities.ExpensesEntity
import com.victorkirui.database.repository.FinanceRepository
import com.victorkirui.finance.domain.FinanceState
import com.victorkirui.finance.domain.FinanceSummaryState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class FinanceViewModel @Inject constructor(
    private val financeRepository: FinanceRepository
): ViewModel() {

    private val _expensesState = MutableStateFlow<List<FinanceState>>(emptyList())
    val expensesState = _expensesState.asStateFlow()

    private val _incomeState = MutableStateFlow<List<FinanceState>>(emptyList())
    val incomeState = _incomeState.asStateFlow()

    private var currentContent by mutableStateOf("Today")

    val currentContentState
        get() = mutableStateOf(currentContent)

    private var _incomeSummaryState = MutableStateFlow(FinanceSummaryState())
    var incomeSummaryState = _incomeSummaryState.asStateFlow()

    private var _expenseSummaryState = MutableStateFlow(FinanceSummaryState())
    var expenseSummaryState = _expenseSummaryState.asStateFlow()


    init {
        viewModelScope.launch {
            getExpensesData()
        }

        viewModelScope.launch {
            getIncomeData()
        }

        viewModelScope.launch {
            getSummaryData()
        }
    }

    private fun dateConverter(date: Date): String {
        val formatter = SimpleDateFormat("dd/MM/yyyy")

        return formatter.format(date)
    }


    private suspend fun getExpensesData(){
        financeRepository.getAllExpensesData().map {
            it.map {expensesEntity->
                FinanceState(
                    name = expensesEntity.expensesName,
                    quantity = expensesEntity.expensesQuantity.toString(),
                    date = dateConverter(expensesEntity.date)
                )
            }
        }.collect{
            _expensesState.value = it
        }
    }

    fun getExpensesData2(): Flow<List<FinanceState>> {

        return financeRepository.getAllExpensesData().map {
            it.map { expensesEntity ->
                FinanceState(
                    name = expensesEntity.expensesName,
                    quantity = expensesEntity.expensesQuantity.toString(),
                    date = dateConverter(expensesEntity.date)
                )
            }
        }
    }

    private suspend fun getIncomeData(){
        financeRepository.getAllIncomeData().map {
            it.map {incomeEntity ->
                FinanceState(
                    name = incomeEntity.incomeName,
                    quantity = "${incomeEntity.incomeQuantity}",
                    date = dateConverter(incomeEntity.date)
                )
            }
        }.collect{
            _incomeState.value = it
        }
    }


    init {
        switchContent()
    }

    private fun switchContent() {
        val nextContent = when (currentContent) {
            "Today" -> "ThisWeek"
            "ThisWeek" -> "ThisMonth"
            "ThisMonth" -> "Today"
            else -> {"Today"}
        }
        currentContent = nextContent

        onSwitchContentDelayed()
    }

    private fun onSwitchContentDelayed() {
        // Schedule the next content switch after 5 seconds
        viewModelScope.launch {
            delay(5000)
            switchContent()
        }
    }

    private suspend fun getSummaryData(){
        val currentDate = LocalDate.now()
        val startOfTheWeek = currentDate.minusDays(currentDate.dayOfWeek.value.toLong() - DayOfWeek.MONDAY.value.toLong())
        val startOfTheMonth = currentDate.withDayOfMonth(1)
        val nextDay = currentDate.plusDays(1)

        val currentDateAsDate = Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant())
        val startOfTheWeekAsDate = Date.from(startOfTheWeek.atStartOfDay(ZoneId.systemDefault()).toInstant())
        val startOfTheMonthAsDate = Date.from(startOfTheMonth.atStartOfDay(ZoneId.systemDefault()).toInstant())
        val nextDayAsDate = Date.from(nextDay.atStartOfDay(ZoneId.systemDefault()).toInstant())

        var incomeDataForToday = 0.0
        financeRepository.getTodayIncome(currentDay = currentDateAsDate, nextDay = nextDayAsDate).collect{
            incomeDataForToday = it
        }

        var incomeDataForTheWeek = 0.0
        financeRepository.getThisWeekIncome(startOfTheWeekAsDate).collect{
            incomeDataForTheWeek = it
        }

        var incomeDataForTheMonth = 0.0
        financeRepository.getThisMonthIncome(startOfTheMonthAsDate).collect{
            incomeDataForTheMonth = it
        }

        var expenseDataForTheCurrentDay = 0.0
        financeRepository.getTodayExpenses(currentDay = currentDateAsDate, nextDay = nextDayAsDate).collect{
            expenseDataForTheCurrentDay = it
        }

        var expenseDataForTheWeek = 0.0
        financeRepository.thisWeekExpenses(startOfTheWeekAsDate).collect{
            expenseDataForTheWeek = it
        }

        var expenseDataForTheMonth = 0.0
        financeRepository.thisMonthExpenses(startOfTheMonthAsDate).collect{
            expenseDataForTheMonth = it
        }

        _incomeSummaryState.update {
            it.copy(
                today = incomeDataForToday,
                thisWeek = incomeDataForTheWeek,
                thisMonth = incomeDataForTheMonth
            )
        }

        _expenseSummaryState.update {
            it.copy(
                today = expenseDataForTheCurrentDay,
                thisWeek = expenseDataForTheWeek,
                thisMonth = expenseDataForTheMonth
            )
        }

    }
}