package com.andymariscal.shared.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "sets",
    foreignKeys = [
        ForeignKey(
            entity = TrackingEntity::class,
            parentColumns = arrayOf("uid"),
            childColumns = arrayOf("tracking_id")
        ),
        ForeignKey(
            entity = ExerciseEntity::class,
            parentColumns = arrayOf("uid"),
            childColumns = arrayOf("exercise_id")
        )
    ]
)
data class SetEntity(
    val uid: Int,
    @ColumnInfo(name = "order")
    val order: String,
    @ColumnInfo(name = "rep_speed_goal")
    val repSpeedGoal: Int,
    @ColumnInfo(name = "rep_speed_real")
    val repSpeedReal: Int,
    @ColumnInfo(name = "reps_goal")
    val repsGoal: Int,
    @ColumnInfo(name = "reps_real")
    val repsReal: Int,
    @ColumnInfo(name = "amount_goal")
    val amountGoal: Int,
    @ColumnInfo(name = "amount_real")
    val amountReal: Int,
    @ColumnInfo(name = "rest_time_goal")
    val restTimeGoal: Int,
    @ColumnInfo(name = "rest_time_real")
    val restTimeReal: Int,
    @ColumnInfo(name = "started_time")
    val startedTime: String,
    @ColumnInfo(name = "completed_time")
    val completedTime: String,
    @ColumnInfo(name = "tracking_id")
    val trackingId: Int,
    @ColumnInfo(name = "exercise_id")
    val exerciseId: Int
)