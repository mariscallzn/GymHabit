package com.andymariscal.gymhabit.ui.assistant.di

import com.andymariscal.gymhabit.ui.assistant.AssistantFragment
import dagger.Subcomponent

@Subcomponent(modules = [AssistantModule::class])
interface AssistantComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): AssistantComponent
    }

    fun inject(assistantFragment: AssistantFragment)
}