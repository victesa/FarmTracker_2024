package com.victorkirui.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class AnimalBasicInfoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "animal_name") val name: String,
    @ColumnInfo(name = "age")val age: Int?,
    @ColumnInfo(name = "animal_breed")val breed: String?,
    @ColumnInfo(name = "animal_gender")val gender: String,
    @ColumnInfo(name = "animal_type") val type: String,
    @ColumnInfo(name = "animal_description") val description: String?,
    @ColumnInfo(name = "animal_image") val animalImage: String?
)
