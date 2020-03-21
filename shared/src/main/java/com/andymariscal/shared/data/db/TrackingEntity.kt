package com.andymariscal.shared.data.db

import androidx.room.*

@Entity(
    tableName = "tracking",
    indices = [
        Index(value = ["session_id"], unique = false),
        Index(value = ["set_type_id"], unique = false)
    ],
    foreignKeys = [
        ForeignKey(
            entity = SessionEntity::class,
            parentColumns = arrayOf("uid"),
            childColumns = arrayOf("session_id")
        ),
        ForeignKey(
            entity = SetTypeEntity::class,
            parentColumns = arrayOf("uid"),
            childColumns = arrayOf("set_type_id")
        )
    ]
)
data class TrackingEntity(
    @PrimaryKey(autoGenerate = true)
    val uid: Int,
    @ColumnInfo(name = "session_id")
    val sessionId: Int,
    @ColumnInfo(name = "set_type_id")
    val setTypeId: Int
)