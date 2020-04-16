package com.andymariscal.gymhabit.ui.assistant.delegate

import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.andymariscal.gymhabit.R
import com.andymariscal.gymhabit.ui.assistant.AssistantViewModel
import com.andymariscal.shared.inf.ViewHolderBinder
import com.andymariscal.shared.inf.ViewType
import com.andymariscal.shared.inf.ViewTypeDelegateAdapter
import com.andymariscal.shared.utils.UiString
import com.andymariscal.shared.utils.inflate
import kotlinx.android.synthetic.main.item_simple_string.view.*

class NumberWidgetDelegate(val viewModel: ViewModel) : ViewTypeDelegateAdapter() {

    //region ViewTypeDelegateAdapter
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
        ViewHolder(parent)

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder?, viewType: ViewType) {
        (viewHolder as ViewHolder).bind(viewType as UiString)
    }
    //endregion

    //region ViewHolder
    inner class ViewHolder(
        parent: ViewGroup
    ) : ViewHolderBinder<UiString>(
        parent.inflate(R.layout.item_simple_string)
    ) {
        override fun bind(uiModel: UiString) {
            viewModel as AssistantViewModel
            itemView.apply {
                tv_text.text = uiModel.text
                uiModel.action?.let { action ->
                    setOnClickListener {
                        when (action.valueType) {
                            "double" -> {
                                viewModel.replayBackWith(UiString(text = "Got it: 12.5"))
                                viewModel.widgetListener(action, 12.5)
                            }
                            "integer" -> {
                                viewModel.replayBackWith(UiString(text = "Ok 8 it's fine"))
                                viewModel.widgetListener(action, 8)
                            }
                        }
                        viewModel.navigateFlowSequence()
                    }
                }
            }
        }
    }
    //endregion
}