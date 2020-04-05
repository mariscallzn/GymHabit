package com.andymariscal.shared.data.source.local

import android.content.res.AssetManager
import com.andymariscal.model.workout.AssistantFlowSequence
import com.andymariscal.shared.data.Result
import com.andymariscal.shared.data.source.AssistantDataSource
import com.andymariscal.shared.utils.IOUtils
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AssistantLocalDataSource(
    private val assetManager: AssetManager,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : AssistantDataSource {

    @Suppress("BlockingMethodInNonBlockingContext")
    override suspend fun createWorkoutFlowSequence(): Result<AssistantFlowSequence> =
        withContext(ioDispatcher) {
            val createWorkoutJson = IOUtils.convertToString(
                assetManager.open("flowsequence/fs_create_workout.json")
            )
            createWorkoutJson?.let { json ->
                Moshi.Builder()
                    .build()
                    .adapter(AssistantFlowSequence::class.java)
                    .fromJson(json)?.let {
                        Result.Success(it)
                    } ?: return@let null
            } ?: Result.Error(Exception("fs_create_workout.json couldn't be parsed"))
        }

}