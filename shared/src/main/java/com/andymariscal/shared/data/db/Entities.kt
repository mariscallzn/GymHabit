package com.andymariscal.shared.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "exercises",
    foreignKeys = [
        ForeignKey(
            entity = MuscleEntity::class,
            parentColumns = arrayOf("uid"),
            childColumns = arrayOf("muscle_id")
        ),
        ForeignKey(
            entity = EquipmentEntity::class,
            parentColumns = arrayOf("uid"),
            childColumns = arrayOf("equipment_id")
        ),
        ForeignKey(
            entity = ExerciseTypeEntity::class,
            parentColumns = arrayOf("uid"),
            childColumns = arrayOf("exercise_type_id")
        )
    ]
)
data class ExerciseEntity(
    @PrimaryKey(autoGenerate = true)
    val uid: Int = 0,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "muscle_id")
    val muscleId: Int,
    @ColumnInfo(name = "equipment_id")
    val equipmentId: Int,
    @ColumnInfo(name = "exercise_type_id")
    val exerciseTypeId: Int
)

@Entity(tableName = "muscles")
data class MuscleEntity(
    @PrimaryKey
    val uid: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "res_id")
    val resId: Int
)

@Entity(tableName = "equipments")
data class EquipmentEntity(
    @PrimaryKey
    val uid: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "res_id")
    val resId: Int
)

@Entity(tableName = "exercise_types")
data class ExerciseTypeEntity(
    @PrimaryKey
    val uid: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "res_id")
    val resId: Int
)