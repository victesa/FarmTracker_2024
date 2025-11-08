package com.victorkirui.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "income_table")
data class IncomeEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val incomeName: String,
    val incomeQuantity: Double,
    val date: Date,
    val incomeDescription: String
)