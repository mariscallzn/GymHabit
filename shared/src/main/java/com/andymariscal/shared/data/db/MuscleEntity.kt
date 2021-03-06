package com.andymariscal.shared.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "muscles")
data class MuscleEntity(
    @PrimaryKey
    val uid: Int,
    @ColumnInfo(name = "name")
    val name: String
)