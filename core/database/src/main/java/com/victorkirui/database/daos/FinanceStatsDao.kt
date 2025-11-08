package com.victorkirui.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.victorkirui.database.entities.FinanceStats
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.util.Date

@Dao
interface FinanceStatsDao {

    @Insert
    suspend fun insert(financeStats: FinanceStats)

    @Delete
    suspend fun delete(financeStats: FinanceStats)


    //Query for profits per duration
    @Query("SELECT SUM(profit) FROM finance_stats_table WHERE date BETWEEN :startOfTheWeek AND :currentDate")
    fun getTotalProfitForTheWeek(startOfTheWeek: String, currentDate: String): Flow<Int>

    @Query("SELECT SUM(profit) FROM finance_stats_table WHERE date BETWEEN :startOfTheMonth AND :currentDate")
    fun getTotalProfitForTheMonth(startOfTheMonth: String, currentDate: String): Flow<Int>

    @Query("SELECT SUM(profit) FROM finance_stats_table WHERE date = :currentDate")
    fun getTotalProfitForTheCurrentDay(currentDate: String): Flow<Int>


    //Query for expenses per duration
    @Query("SELECT SUM(expenses) FROM finance_stats_table WHERE date BETWEEN :startOfTheWeek AND :currentDate")
    fun getTotalExpensesForTheWeek(startOfTheWeek: String, currentDate: String): Flow<Int>

    @Query("SELECT SUM(expenses) FROM finance_stats_table WHERE date BETWEEN :startOfTheMonth AND :currentDate")
    fun getTotalExpensesForTheMonth(startOfTheMonth: String, currentDate: String): Flow<Int>

    @Query("SELECT SUM(expenses) FROM finance_stats_table WHERE date = :currentDate")
    fun getTotalExpensesForTheDay(currentDate: String): Flow<Int>


    //Query for the netIncome per duration
    @Query("SELECT SUM(netIncome) FROM finance_stats_table WHERE date = :currentDate")
    fun getTotalNetIncomeForTheDay(currentDate: String): Flow<Int>

    @Query("SELECT SUM(netIncome) FROM finance_stats_table WHERE date BETWEEN :startOfTheWeek AND :currentDate")
    fun getTotalNetIncomeForTheWeek(startOfTheWeek: String, currentDate: String): Flow<Int>

    @Query("SELECT SUM(netIncome) FROM finance_stats_table WHERE date BETWEEN :startOfTheMonth AND :currentDate")
    fun getTotalNetIncomeForTheMonth(startOfTheMonth: String, currentDate: String): Flow<Int>


    //Query for number of rows
    @Query("SELECT COUNT(id) FROM finance_stats_table")
    fun getNumberOfColumns(): Flow<Int>

}