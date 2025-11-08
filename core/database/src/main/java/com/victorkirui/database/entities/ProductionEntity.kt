package com.victorkirui.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "production_entity")
data class ProductionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val productName: String,
    val productQuantity: Int,
    val productUnit: String,
    val date: String
)