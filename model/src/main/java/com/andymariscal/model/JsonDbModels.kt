package com.andymariscal.model

import com.squareup.moshi.JsonClass

//region PrePopulateData
@JsonClass(generateAdapter = true)
data class PrePopulateData(
    val version: Int,
    val exercises: List<Exercises>,
    val exerciseTypes: List<ExerciseTypes>,
    val equipments: List<Equipments>,
    val muscles: List<Muscles>
)
//endregion

//region Exercises
@JsonClass(generateAdapter = true)
data class Exercises(
    val name: String,
    val description: String,
    val muscleId: Int,
    val equipmentId: Int,
    val exerciseTypeId: Int
)
//endregion

//region Muscles
@JsonClass(generateAdapter = true)
data class Muscles(
    val uid: Int,
    val name: String
)
//endregion

//region ExerciseTypes
@JsonClass(generateAdapter = true)
data class ExerciseTypes(
    val uid: Int,
    val name: String
)
//endregion

//region Equipments
@JsonClass(generateAdapter = true)
data class Equipments(
    val uid: Int,
    val name: String
)
//endregion