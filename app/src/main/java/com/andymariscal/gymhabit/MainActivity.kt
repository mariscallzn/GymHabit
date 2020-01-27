package com.andymariscal.gymhabit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.andymariscal.model.*
import com.squareup.moshi.Moshi

import timber.log.Timber
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MainApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val workout = Workout(
            exercises = listOf(
                Exercise(
                    name = "Super Set Chest",
                    exerciseType = "strength",
                    muscleTargeted = "chest",
                    setType = "super_set",
                    sets = listOf(
                        SuperSet(
                            completed = false,
                            restTime = "30 sec",
                            exercises = listOf(
                                Exercise(
                                    name = "Chest press",
                                    exerciseType = "strength",
                                    muscleTargeted = "chest",
                                    setType = "singles",
                                    sets = listOf(
                                        SingleSet(
                                            completed = false,
                                            restTime = "0",
                                            weight = "90 lb",
                                            reps = "12"
                                        )
                                    )
                                ),
                                Exercise(
                                    name = "Incline Chest press",
                                    exerciseType = "strength",
                                    muscleTargeted = "chest",
                                    setType = "singles",
                                    sets = listOf(
                                        SingleSet(
                                            completed = false,
                                            restTime = "0",
                                            weight = "40 lb",
                                            reps = "16"
                                        )
                                    )
                                ),
                                Exercise(
                                    name = "Inner Chest with Plates",
                                    exerciseType = "strength",
                                    muscleTargeted = "chest",
                                    setType = "singles",
                                    sets = listOf(
                                        SingleSet(
                                            completed = false,
                                            restTime = "0",
                                            weight = "20 lb",
                                            reps = "18"
                                        )
                                    )
                                )
                            )
                        )
                    )
                ),
                Exercise(
                    name = "Single",
                    setType = "Singles",
                    muscleTargeted = "Singles",
                    exerciseType = "singles",
                    sets = listOf(
                        SingleSet(
                            completed = false,
                            reps = "12",
                            weight = "23",
                            restTime = "20 sec"
                        )
                    )
                )
            )
        )

        val adapter = Moshi.Builder().build().adapter<Workout>(Workout::class.java)
        Timber.e(adapter.toJson(workout))

        workout.exercises.forEach {
            
        }
    }
}
