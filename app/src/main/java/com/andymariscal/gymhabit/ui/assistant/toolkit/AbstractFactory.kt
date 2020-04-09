package com.andymariscal.gymhabit.ui.assistant.toolkit

import androidx.lifecycle.ViewModel
import com.andymariscal.shared.data.db.AppDatabase
import com.andymariscal.shared.inf.ViewType
import com.andymariscal.shared.inf.ViewTypeDelegate

sealed class FactoryComponent {
    data class ActionComponent(val appDatabase: AppDatabase) : FactoryComponent()

    //For now it's just an object until we actually need something as a dependency
    object AssistantComponent : FactoryComponent()
}

abstract class AbstractFactory {

    companion object {

        private var actionToolkit: ActionsFactory? = null
        private var assistantToolkit: AssistantFactory? = null

        fun getFactory(factoryComponent: FactoryComponent): AbstractFactory {
            return when (factoryComponent) {
                is FactoryComponent.ActionComponent -> {
                    if(actionToolkit == null) {
                        actionToolkit = ActionsFactory(factoryComponent.appDatabase)
                    }
                    actionToolkit!!
                }
                is FactoryComponent.AssistantComponent -> {
                    if(assistantToolkit == null) {
                        assistantToolkit = AssistantFactory()
                    }
                    assistantToolkit!!
                }
            }
        }
    }



    suspend fun getItems(items: List<Any>): List<ViewType> =
        items.map { getItem(it) }

    abstract suspend fun getItem(type: Any): ViewType
    abstract fun getViewTypeDelegates(viewModel: ViewModel): Array<ViewTypeDelegate>
}