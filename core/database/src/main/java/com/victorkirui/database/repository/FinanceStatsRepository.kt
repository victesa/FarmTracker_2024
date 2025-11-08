package com.victorkirui.database.repository

import com.victorkirui.database.entities.FinanceStats
import kotlinx.coroutines.flow.Flow
import java.util.Date

interface FinanceStatsRepository {
    fun getAllFinanceStats(): Flow<List<FinanceStats>>

    suspend fun insertFinanceStats(financeStats: FinanceStats)

    suspend fun deleteFinanceStats(financeStats: FinanceStats)

    fun getAllFinanceStatsForTheWeek(startOfTheWeek: Date, currentDate: Date): Flow<List<FinanceStats>>

    fun getAllFinanceStatsForTheDay(currentDate: Date): Flow<List<FinanceStats>>

    fun getAllFinanceStatsForTheMonth(currentDate: Date, startOfTheMonth: Date): Flow<List<FinanceStats>>
}