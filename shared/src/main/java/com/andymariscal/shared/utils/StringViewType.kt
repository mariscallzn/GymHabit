package com.andymariscal.shared.utils

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andymariscal.shared.R
import com.andymariscal.shared.inf.*
import kotlinx.android.synthetic.main.item_simple_string.view.*

//region Simple String Delegate
class SimpleStringDelegate(
    private val onActionClickListener: ActionClickListener? = null
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
                    onActionClickListener?.onActionClicked()
                }
            }
        }
    }
//endregion
}

//region Generic Click listener
interface ActionClickListener {
    fun onActionClicked()
}
//endregion

//region String ViewTypeDelegate
val StringViewType = ViewTypeDelegate(STRING_VIEW_TYPE, SimpleStringDelegate())
//endregion

//endregion