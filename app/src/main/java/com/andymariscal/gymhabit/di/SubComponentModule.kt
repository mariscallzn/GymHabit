package com.andymariscal.gymhabit.di

import com.andymariscal.gymhabit.ui.assistant.di.AssistantComponent
import dagger.Module

@Module(
    subcomponents = [
        AssistantComponent::class
    ]
)
interface SubComponentModule