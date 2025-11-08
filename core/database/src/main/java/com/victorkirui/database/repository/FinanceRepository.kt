package com.victorkirui.database.repository

import com.victorkirui.database.entities.ExpensesEntity
import com.victorkirui.database.entities.IncomeEntity
import kotlinx.coroutines.flow.Flow
import java.util.Date

interface FinanceRepository {

    fun getAllExpensesData(): Flow<List<ExpensesEntity>>

    fun getAllIncomeData(): Flow<List<IncomeEntity>>



    fun getTodayIncome(currentDay: Date, nextDay: Date): Flow<Double>

    fun getThisWeekIncome(startOfWeek: Date): Flow<Double>

    fun getThisMonthIncome(startOfThisMonth: Date): Flow<Double>


    fun getTodayExpenses(currentDay: Date, nextDay: Date): Flow<Double>

    fun thisWeekExpenses(startOfWeek: Date): Flow<Double>

    fun thisMonthExpenses(startOfThisMonth: Date): Flow<Double>

    suspend fun insertIncome(name: String,
                             amount: Double,
                             date: Date,
                             description: String)

    suspend fun insertExpenses(name: String,
                               amount: Double,
                               date: Date,
                               description: String)
}