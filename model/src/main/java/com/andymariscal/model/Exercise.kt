package com.andymariscal.model

import com.squareup.moshi.JsonClass

//TODO Think in a better way to represent the values other than strings, The kotlin version of enum ie
@JsonClass(generateAdapter = true)
open class Exercise<T>(
    val name: String,
    val exerciseType: String,
    val muscleTargeted: String,
    val setType: String,
    val sets: List<T>
)