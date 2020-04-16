package com.andymariscal.gymhabit.ui.assistant.toolkit

import androidx.lifecycle.ViewModel
import com.andymariscal.gymhabit.ui.assistant.delegate.ExercisesDelegate
import com.andymariscal.gymhabit.ui.assistant.delegate.MoveToDelegate
import com.andymariscal.gymhabit.ui.assistant.delegate.NumberWidgetDelegate
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
private const val EXERCISE_PICKER_VIEW_TYPE = 2
private const val NUMBER_WIDGET_VIEW_TYPE = 3
private const val DATE_PICKER_WIDGET_VIEW_TYPE = 4
private const val MOVE_TO_VIEW_TYPE = 5
//endregion

//region Action types constants
private const val PICKER_WIDGET = "picker_widget"
private const val PICKER_WIDGET_EXERCISES = "exercises"
private const val NUMBER_WIDGET = "number_widget"
private const val DATE_PICKER_WIDGET = "date_picker_widget"
private const val MOVE_TO = "move_to"
//endregion

class ActionsFactory(
    private val appDatabase: AppDatabase,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : AbstractFactory() {

    override suspend fun getItem(type: Any): ViewType =
        withContext(ioDispatcher) {
            val action = type as Action
            when (action.actionType) {
                PICKER_WIDGET -> {
                    when (action.metadata) {
                        PICKER_WIDGET_EXERCISES -> showExerciseCatalog(action)
                        else -> showError(action.valueType)
                    }
                }
                NUMBER_WIDGET -> showNumberWidget(action)
                MOVE_TO -> showMoveTo(action)
                else -> showError("--${action.valueType}")
            }
        }

    override fun getViewTypeDelegates(viewModel: ViewModel): Array<ViewTypeDelegate> = arrayOf(
        ViewTypeDelegate(MUSCLE_PICKER_VIEW_TYPE, SimpleStringDelegate(viewModel)),
        ViewTypeDelegate(EXERCISE_PICKER_VIEW_TYPE, ExercisesDelegate(viewModel)),
        ViewTypeDelegate(NUMBER_WIDGET_VIEW_TYPE, NumberWidgetDelegate(viewModel)),
        ViewTypeDelegate(MOVE_TO_VIEW_TYPE, MoveToDelegate(viewModel))
    )

    //region Private Inner logic
    private fun showError(errorType: String): ViewType {
        //TODO  when(errorType){ Bla Bla}
        return UiString(text = "Error: $errorType")
    }

    private fun showExerciseCatalog(action: Action): ViewType {
        return UiString(
            viewTypeId = EXERCISE_PICKER_VIEW_TYPE,
            text = "List of exercises:",
            action = action
        )
    }

    private fun showNumberWidget(action: Action): ViewType {
        return UiString(
            viewTypeId = NUMBER_WIDGET_VIEW_TYPE,
            text = "TODO: NumberWidget : ${action.valueType}",
            action = action)
    }

    private fun showMoveTo(action: Action): ViewType {
        return UiString(
            viewTypeId = MOVE_TO_VIEW_TYPE,
            text = "Move To: ${action.valueType}",
            action = action)
    }
    //endregion
}
