package com.victorkirui.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.victorkirui.database.entities.IncomeEntity
import kotlinx.coroutines.flow.Flow
import java.util.Date

@Dao
interface IncomeDao {

    @Query("SELECT * FROM income_table ORDER BY date ASC")
    fun getAllIncomeData(): Flow<List<IncomeEntity>>

    @Query("SELECT SUM(incomeQuantity) AS total_income_today FROM income_table WHERE date >= :currentDay AND date < :nextDay")
    fun getIncomeForToday(currentDay: Date, nextDay: Date): Flow<Double>

    @Query("SELECT SUM(incomeQuantity) AS total_income_this_week FROM income_table WHERE date >= :startOfWeek")
    fun getIncomeForCurrentWeek(startOfWeek: Date): Flow<Double>

    @Query("SELECT SUM(incomeQuantity) AS total_income_this_month FROM income_table WHERE date >= :startOfThisMonth")
    fun getIncomeForThisMonth(startOfThisMonth: Date): Flow<Double>

    @Insert
    suspend fun insertIncome(incomeEntity: IncomeEntity)

}
