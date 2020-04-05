package com.andymariscal.gymhabit.ui.assistant.di

import androidx.lifecycle.ViewModel
import com.andymariscal.gymhabit.ui.assistant.AssistantViewModel
import com.andymariscal.gymhabit.ui.assistant.toolkit.FactoryComponent
import com.andymariscal.shared.data.db.AppDatabase
import com.andymariscal.shared.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Named

const val NAMED_ACTION_COMPONENT = "action_component"
const val NAMED_ASSISTANT_COMPONENT = "assistant_component"

//TODO Add Scope
@Module(includes = [AssistantModule.AssistantViewModelModule::class])
class AssistantModule {

    @Provides
    @Named(NAMED_ACTION_COMPONENT)
    fun providesActionFactoryComponent(appDatabase: AppDatabase): FactoryComponent =
        FactoryComponent.ActionComponent(appDatabase)

    @Provides
    @Named(NAMED_ASSISTANT_COMPONENT)
    fun providesAssistantFactoryComponent(): FactoryComponent =
        FactoryComponent.AssistantComponent

    @Module
    abstract class AssistantViewModelModule {
        @Binds
        @IntoMap
        @ViewModelKey(AssistantViewModel::class)
        abstract fun bindViewModel(viewModel: AssistantViewModel): ViewModel
    }

}