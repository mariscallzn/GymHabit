package com.andymariscal.shared.data.workout

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface WorkoutRepository {
    suspend fun testCourutines(): String
}

class DefaultWorkoutRepository : WorkoutRepository {
    override suspend fun testCourutines(): String =
        withContext(Dispatchers.IO) {
            Thread.sleep(3000)
            "DONE"
        }
}