package com.andymariscal.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class SingleSet(
    val completed: Boolean,
    val restTime: String,
    val weight: String,
    val reps: String
) : SetType()