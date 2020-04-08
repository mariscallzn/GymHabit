package com.andymariscal.gymhabit.ui.assistant

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andymariscal.gymhabit.ui.assistant.di.NAMED_ACTION_COMPONENT
import com.andymariscal.gymhabit.ui.assistant.di.NAMED_ASSISTANT_COMPONENT
import com.andymariscal.gymhabit.ui.assistant.operation.OperationDependencies
import com.andymariscal.gymhabit.ui.assistant.operation.OperationFactory
import com.andymariscal.gymhabit.ui.assistant.toolkit.AbstractFactory
import com.andymariscal.gymhabit.ui.assistant.toolkit.FactoryComponent
import com.andymariscal.model.workout.Action
import com.andymariscal.model.workout.FlowSequence
import com.andymariscal.shared.data.Result
import com.andymariscal.shared.data.succeeded
import com.andymariscal.shared.domain.LoadFlowSequenceUseCase
import com.andymariscal.shared.inf.DSFlowSequence
import com.andymariscal.shared.inf.DefaultDiffCallback
import com.andymariscal.shared.inf.ItemDelegateAdapter
import com.andymariscal.shared.inf.ViewType
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

class AssistantViewModel @Inject constructor(
    private val useCase: LoadFlowSequenceUseCase,
    @Named(NAMED_ACTION_COMPONENT) private val actionsComponent: FactoryComponent,
    @Named(NAMED_ASSISTANT_COMPONENT) private val assistantComponent: FactoryComponent,
    private val operationDependencies: OperationDependencies
) : ViewModel() {

    //region Init
    init {
        loadFlowSequence("create_workout")
    }
    //endregion

    //region Global properties
    private var currentFlowSequence: DSFlowSequence<FlowSequence>? = null

    private val chatHistory = mutableListOf<ViewType>()
    private val actionsItems = mutableListOf<ViewType>()

    val assistantAdapter by lazy {
        ItemDelegateAdapter(
            ItemDelegateAdapter.Builder(DefaultDiffCallback)
                .addAllDelegates(
                    *AbstractFactory.getFactory(assistantComponent)
                        .getViewTypeDelegates(this)
                )
        )
    }

    val actionsAdapter by lazy {
        ItemDelegateAdapter(
            ItemDelegateAdapter.Builder(DefaultDiffCallback)
                .addAllDelegates(
                    *AbstractFactory.getFactory(actionsComponent)
                        .getViewTypeDelegates(this)
                )
        )
    }
    //endregion

    //region Actions
    fun navigateFlowSequence(goToStep: Int = -1) {
        if (goToStep != -1) currentFlowSequence?.moveTo(goToStep)
        viewModelScope.launch {
            currentFlowSequence?.next()?.let {
                addMessages(
                    AbstractFactory.getFactory(assistantComponent)
                        .getItem(it.message)
                )

                addActions(
                    *AbstractFactory.getFactory(actionsComponent)
                        .getItems(it.actions).toTypedArray()
                )
            }
        }
    }

    fun widgetListener(action: Action, value: Any) {
        viewModelScope.launch {
            OperationFactory.getFactory(operationDependencies).evaluate(action, value)
        }
    }
    //endregion

    //region Private Inner logic
    @Suppress("SameParameterValue")
    private fun loadFlowSequence(flowSequenceId: String) {
        operationDependencies.flowSequenceId = flowSequenceId
        viewModelScope.launch {
            useCase(flowSequenceId).apply {
                if (succeeded) {
                    currentFlowSequence = (this as Result.Success).data.dsFlowSequence
                    navigateFlowSequence()
                } else {
                    TODO("Use case crashed")
                }
            }
        }
    }

    private fun addMessages(vararg sequence: ViewType, clearSequence: Boolean = false) {
        chatHistory.apply {
            if (clearSequence) clear()
            addAll(sequence)
            assistantAdapter.submitList(this.toList())
        }
    }

    private fun addActions(vararg actions: ViewType, clearActions: Boolean = true) {
        actionsItems.apply {
            if (clearActions) clear()
            addAll(actions)
            actionsAdapter.submitList(this.toList())
        }
    }
    //endregion
}