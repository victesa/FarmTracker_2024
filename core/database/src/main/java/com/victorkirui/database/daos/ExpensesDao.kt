package com.victorkirui.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.victorkirui.database.entities.ExpensesEntity
import kotlinx.coroutines.flow.Flow
import java.util.Date

@Dao
interface ExpensesDao {

    @Query("SELECT * FROM expenses_entity ORDER BY date DESC")
    fun getAllExpenses(): Flow<List<ExpensesEntity>>

    @Query("SELECT SUM(expensesQuantity) FROM expenses_entity WHERE date >= :currentDay AND date < :nextDay")
    fun getExpensesForToday(currentDay: Date, nextDay: Date): Flow<Double>

    @Query("SELECT SUM(expensesQuantity) FROM expenses_entity WHERE date >= :startOfWeek")
    fun getExpensesForCurrentWeek(startOfWeek: Date): Flow<Double>

    @Query("SELECT SUM(expensesQuantity) FROM expenses_entity WHERE date >= :startOfThisMonth")
    fun getExpensesForThisMonth(startOfThisMonth: Date): Flow<Double>


    @Insert
    suspend fun insertExpenses(expensesEntity: ExpensesEntity)

}