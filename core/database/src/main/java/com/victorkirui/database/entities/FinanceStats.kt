package com.victorkirui.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.util.Date

@Entity(tableName = "finance_stats_table")
data class FinanceStats(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val netIncome: Int = 0,
    val profit: Int = 0,
    val expenses: Int = 0,
    val date: String = ""
)
