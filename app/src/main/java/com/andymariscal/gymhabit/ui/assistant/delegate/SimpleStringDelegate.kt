package com.andymariscal.gymhabit.ui.assistant.delegate

import com.andymariscal.gymhabit.ui.assistant.AssistantViewModel
import com.andymariscal.shared.utils.inflate
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.andymariscal.shared.R
import com.andymariscal.shared.inf.*
import kotlinx.android.synthetic.main.item_simple_string.view.*

//region Simple String Delegate
class SimpleStringDelegate(
    private val viewModel: ViewModel
) : ViewTypeDelegateAdapter() {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder = ViewHolder(parent)
    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder?, viewType: ViewType) {
        (viewHolder as ViewHolder).bind(viewType)
    }

    //region ViewHolder
    inner class ViewHolder(
        parent: ViewGroup
    ) : ViewHolderBinder<ViewType>(parent.inflate(R.layout.item_simple_string)) {
        override fun bind(uiModel: ViewType) {
            itemView.apply {
                tv_text.text = uiModel.getUniqueProperty() as String
                setOnClickListener {
                    (viewModel as AssistantViewModel).navigateFlowSequence()
                }
            }
        }
    }
//endregion
}
//endregion