package com.andymariscal.gymhabit.ui.assistant.toolkit

import androidx.lifecycle.ViewModel
import com.andymariscal.gymhabit.ui.assistant.delegate.SimpleStringDelegate
import com.andymariscal.model.workout.Action
import com.andymariscal.shared.data.db.AppDatabase
import com.andymariscal.shared.inf.ViewType
import com.andymariscal.shared.inf.ViewTypeDelegate
import com.andymariscal.shared.utils.UiString
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

//region ViewType constants
private const val MUSCLE_PICKER_VIEW_TYPE = 1
//endregion

//region Action types constants
private const val PICKER_WIDGET = "picker_widget"
private const val NUMBER_WIDGET = "number_widget"
//endregion

class ActionsFactory(
    private val appDatabase: AppDatabase,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : AbstractFactory() {

    override suspend fun getItem(type: Any): ViewType =
        withContext(ioDispatcher) {
            val action = type as Action
            when (action.actionType) {
                PICKER_WIDGET -> displayPickerWidget(action.value)
                NUMBER_WIDGET -> displayNumberWidget(action.value)
                else -> {
                    UiString("${action.actionType} : ${action.value}")
                }
            }
        }

    override fun getViewTypeDelegates(viewModel: ViewModel): Array<ViewTypeDelegate> = arrayOf(
        ViewTypeDelegate(MUSCLE_PICKER_VIEW_TYPE, SimpleStringDelegate(viewModel))
    )

    //region Private Inner logic
    private fun displayPickerWidget(value: String): ViewType {
        val entity = appDatabase.muscleDao().getAll()[0]
        return UiString("TODO: ${entity.name} : ${entity.uid}")
    }

    private fun displayNumberWidget(value: String) : ViewType {
        return UiString("TODO: NumberWidget : $value")
    }
    //endregion
}
