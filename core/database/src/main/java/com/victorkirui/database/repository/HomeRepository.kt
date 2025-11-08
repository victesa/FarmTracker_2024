package com.victorkirui.database.repository

import com.victorkirui.database.daos.AnimalInfoDao
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    //Expenses
    fun getTotalExpensesForTheDay(currentDate: String): Flow<Int>

    fun getTotalExpensesForTheWeek(startOfTheWeek: String, currentDate: String): Flow<Int>

    fun getTotalExpensesForTheMonth(startOfTheMonth: String, currentDate: String): Flow<Int>


    //Income
    fun getTotalIncomeForTheDay(currentDate: String): Flow<Int>

    fun getTotalIncomeForTheWeek(startOfTheWeek: String, currentDate: String): Flow<Int>

    fun getTotalIncomeForTheMonth(startOfTheMonth: String, currentDate: String): Flow<Int>


    //Profit
    fun getTotalProfitForTheDay(currentDate: String): Flow<Int>

    fun getTotalProfitForTheWeek(startOfTheWeek: String, currentDate: String): Flow<Int>

    fun getTotalProfitForTheMonth(startOfTheMonth: String, currentDate: String): Flow<Int>


    fun getNumberOfColumns(): Flow<Int>

    //Animals
    fun getNumberOfAnimals(): Flow<List<AnimalInfoDao.AnimalNameCount>>
}