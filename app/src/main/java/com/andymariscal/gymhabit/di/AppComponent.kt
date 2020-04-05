package com.andymariscal.gymhabit.di

import android.content.Context
import com.andymariscal.gymhabit.ui.assistant.di.AssistantComponent
import com.andymariscal.shared.di.SharedModule
import com.andymariscal.shared.di.ViewModelBuilderModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        SharedModule::class,
        ViewModelBuilderModule::class,
        SubComponentModule::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

    fun assistantComponent(): AssistantComponent.Factory
}