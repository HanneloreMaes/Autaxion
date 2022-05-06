package com.example.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "carBrands")
data class Car(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val car: String
)