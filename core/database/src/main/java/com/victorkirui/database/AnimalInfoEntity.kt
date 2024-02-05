package com.victorkirui.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AnimalInfoEntity(
    @PrimaryKey
    val id: Int = 0,

    @ColumnInfo(name = "animal_name") val name: String,
    @ColumnInfo(name = "age")val age: Int,
    @ColumnInfo(name = "animal_breed")val breed: String,
    @ColumnInfo(name = "animal_gender")val gender: String,
    @ColumnInfo(name = "animal_type") val type: String
)
