package com.andymariscal.shared.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "tracking_comments",
    foreignKeys = [
        ForeignKey(
            entity = TrackingEntity::class,
            parentColumns = arrayOf("uid"),
            childColumns = arrayOf("tracking_id")
        )
    ]
)
data class TrackingCommentEntity(
    @PrimaryKey(autoGenerate = true)
    val uid: Int,
    @ColumnInfo(name = "comment")
    val comment: String,
    @ColumnInfo(name = "tracking_id")
    val trackingId: Int
)