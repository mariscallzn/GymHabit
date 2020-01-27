package com.andymariscal.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class SuperSet(
    val completed: Boolean,
    val restTime: String,
    val exercises: List<Exercise<SingleSet>>
) : SetType()