package com.victorkirui.database.data

import com.victorkirui.database.daos.ExpensesDao
import com.victorkirui.database.daos.IncomeDao
import com.victorkirui.database.entities.ExpensesEntity
import com.victorkirui.database.entities.IncomeEntity
import com.victorkirui.database.repository.FinanceRepository
import kotlinx.coroutines.flow.Flow
import java.util.Date
import javax.inject.Inject

class FinanceRepositoryImpl @Inject constructor(
    private val expensesDao: ExpensesDao,
    private val incomeDao: IncomeDao): FinanceRepository {

    override fun getAllExpensesData(): Flow<List<ExpensesEntity>> {
        return expensesDao.getAllExpenses()
    }

    override fun getAllIncomeData(): Flow<List<IncomeEntity>> {
        return incomeDao.getAllIncomeData()
    }

    override fun getTodayIncome(currentDay: Date, nextDay: Date): Flow<Double> {
        return incomeDao.getIncomeForToday(currentDay, nextDay)
    }

    override fun getThisWeekIncome(startOfWeek: Date): Flow<Double> {
        return incomeDao.getIncomeForCurrentWeek(startOfWeek)
    }

    override fun getThisMonthIncome(startOfThisMonth: Date): Flow<Double> {
        return incomeDao.getIncomeForThisMonth(startOfThisMonth)
    }

    override fun getTodayExpenses(currentDay: Date, nextDay: Date): Flow<Double> {
        return expensesDao.getExpensesForToday(currentDay, nextDay)
    }

    override fun thisWeekExpenses(startOfWeek: Date): Flow<Double> {
        return expensesDao.getExpensesForCurrentWeek(startOfWeek)
    }

    override fun thisMonthExpenses(startOfThisMonth: Date): Flow<Double> {
        return expensesDao.getExpensesForThisMonth(startOfThisMonth)
    }

    override suspend fun insertIncome(
        name: String,
        amount: Double,
        date: Date,
        description: String
    ) {
        val incomeEntity = IncomeEntity(
            incomeName = name,
            incomeQuantity = amount,
            incomeDescription = description,
            date = date
        )

        incomeDao.insertIncome(incomeEntity)
    }

    override suspend fun insertExpenses(
        name: String,
        amount: Double,
        date: Date,
        description: String
    ) {
        val expensesEntity = ExpensesEntity(
            expensesName = name,
            expensesQuantity = amount,
            date = date,
            expensesDescription = description
        )

        expensesDao.insertExpenses(expensesEntity)
    }

}