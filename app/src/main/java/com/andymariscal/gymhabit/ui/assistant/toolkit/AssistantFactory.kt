package com.andymariscal.gymhabit.ui.assistant.toolkit

import androidx.lifecycle.ViewModel
import com.andymariscal.shared.inf.ViewType
import com.andymariscal.shared.inf.ViewTypeDelegate
import com.andymariscal.shared.utils.SimpleStringDelegate
import com.andymariscal.shared.utils.UiString

const val MESSAGE_VIEW_TYPE = 1

class AssistantFactory : AbstractFactory() {
    override suspend fun getItem(type: Any): ViewType {
        return UiString(text = type as String)
    }

    override fun getViewTypeDelegates(viewModel: ViewModel): Array<ViewTypeDelegate> = arrayOf(
        ViewTypeDelegate(MESSAGE_VIEW_TYPE, SimpleStringDelegate())
    )
}