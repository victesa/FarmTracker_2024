package com.victorkirui.database.data

import com.victorkirui.database.daos.AnimalInfoDao
import com.victorkirui.database.daos.FinanceStatsDao
import com.victorkirui.database.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val financeStatsDao: FinanceStatsDao,
    private val animalInfoDao: AnimalInfoDao
): HomeRepository {
    override fun getTotalExpensesForTheDay(currentDate: String): Flow<Int> {
        return financeStatsDao.getTotalExpensesForTheDay(currentDate =currentDate)
    }

    override fun getTotalExpensesForTheWeek(
        startOfTheWeek: String,
        currentDate: String
    ): Flow<Int> {
       return  financeStatsDao.getTotalExpensesForTheWeek(
           startOfTheWeek = startOfTheWeek, currentDate = currentDate
       )
    }

    override fun getTotalExpensesForTheMonth(
        startOfTheMonth: String,
        currentDate: String
    ): Flow<Int> {
        return financeStatsDao.getTotalExpensesForTheMonth(
            startOfTheMonth = startOfTheMonth,
            currentDate = currentDate
        )
    }

    override fun getTotalIncomeForTheDay(currentDate: String): Flow<Int> {
        return financeStatsDao.getTotalNetIncomeForTheDay(currentDate = currentDate)
    }

    override fun getTotalIncomeForTheWeek(startOfTheWeek: String, currentDate: String): Flow<Int> {
        return financeStatsDao.getTotalNetIncomeForTheWeek(
            startOfTheWeek = startOfTheWeek,
            currentDate = currentDate
        )
    }

    override fun getTotalIncomeForTheMonth(
        startOfTheMonth: String,
        currentDate: String
    ): Flow<Int> {
        return financeStatsDao.getTotalNetIncomeForTheMonth(
            startOfTheMonth = startOfTheMonth,
            currentDate = currentDate
        )
    }

    override fun getTotalProfitForTheDay(currentDate: String): Flow<Int> {
        return financeStatsDao.getTotalProfitForTheCurrentDay(
            currentDate
        )
    }

    override fun getTotalProfitForTheWeek(startOfTheWeek: String, currentDate: String): Flow<Int> {
        return financeStatsDao.getTotalProfitForTheWeek(
            startOfTheWeek = startOfTheWeek,
            currentDate = currentDate
        )
    }

    override fun getTotalProfitForTheMonth(
        startOfTheMonth: String,
        currentDate: String
    ): Flow<Int> {
        return financeStatsDao.getTotalProfitForTheMonth(
            startOfTheMonth = startOfTheMonth,
            currentDate = currentDate
        )
    }

    override fun getNumberOfColumns(): Flow<Int> {
        return financeStatsDao.getNumberOfColumns()
    }

    override fun getNumberOfAnimals(): Flow<List<AnimalInfoDao.AnimalNameCount>> {
        return animalInfoDao.getNumberOfAnimals()
    }


}