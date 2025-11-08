package com.victorkirui.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "expenses_entity")
data class ExpensesEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val expensesName: String,
    val date: Date,
    val expensesQuantity: Double,
    val expensesDescription: String
)
