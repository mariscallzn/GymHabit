package com.andymariscal.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Workout(
    val exercises: List<Exercise<out SetType>>
)
