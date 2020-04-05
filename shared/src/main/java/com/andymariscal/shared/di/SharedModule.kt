package com.andymariscal.shared.di

import android.content.Context
import com.andymariscal.shared.data.source.AssistantDataSource
import com.andymariscal.shared.data.source.local.AssistantLocalDataSource
import com.andymariscal.shared.data.flowsequence.DefaultFlowSequenceRepository
import com.andymariscal.shared.data.flowsequence.FlowSequenceRepository
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Named
import javax.inject.Singleton

const val NAMED_LOCAL_ASSISTANT_DATA_SOURCE = "localAssistanceDataSource"

@Module
class SharedModule {

    //TODO Create Qualifiers for IO, Default etc...
    @Singleton
    @Provides
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Singleton
    @Provides
    @Named(NAMED_LOCAL_ASSISTANT_DATA_SOURCE)
    fun providesLocalDataSource(
        context: Context
    ): AssistantDataSource = AssistantLocalDataSource(assetManager = context.assets)

    //TODO Andres: Review this named annotation
    @Singleton
    @Provides
    fun providesWorkoutRepository(
        @Named(NAMED_LOCAL_ASSISTANT_DATA_SOURCE) localAssistantDataSource: AssistantDataSource
    ): FlowSequenceRepository = DefaultFlowSequenceRepository(localAssistantDataSource)
}