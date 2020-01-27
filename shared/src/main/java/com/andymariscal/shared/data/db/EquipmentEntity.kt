package com.andymariscal.shared.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "equipments")
data class EquipmentEntity(
    @PrimaryKey
    val uid: Int,
    @ColumnInfo(name = "name")
    val name: String
)