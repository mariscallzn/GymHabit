package com.andymariscal.gymhabit.ui.createworkout.delegate

import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andymariscal.gymhabit.R
import com.andymariscal.shared.inf.*
import com.andymariscal.shared.utils.getAdapterAsDelegate
import com.andymariscal.shared.utils.inflate
import kotlinx.android.synthetic.main.item_title_and_date.view.*

class TitleAndDateDelegate() : ViewTypeDelegateAdapter() {

    //region ViewTypeDelegateAdapter
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
        ViewHolder(parent)

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder?, viewType: ViewType) {
        (viewHolder as ViewHolder).bind(viewType)
    }
    //endregion

    //region ViewHolder
    inner class ViewHolder(
        parent: ViewGroup
    ) : ViewHolderBinder<ViewType>(
        parent.inflate(R.layout.item_title_and_date)
    ) {
        override fun bind(uiModel: ViewType) {
            itemView.apply {
                rv_date_picker.apply {
                    layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    adapter = ItemDelegateAdapter(ItemDelegateAdapter.Builder(DefaultDiffCallback)
                        .addAllDelegates(ViewTypeDelegate(1, DatePickerDelegate())))
                }

                rv_date_picker.getAdapterAsDelegate()?.submitList(StringConverter.convertList(listOf(
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    ""
                )))
            }
        }
    }
    //endregion

    //region DateDelegate
    class DatePickerDelegate : ViewTypeDelegateAdapter() {
        //region ViewTypeDelegateAdapter
        override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
            ViewHolder(parent)

        override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder?, viewType: ViewType) {
            (viewHolder as ViewHolder).bind(viewType)
        }
        //endregion

        //region ViewHolder
        inner class ViewHolder(
            parent: ViewGroup
        ) : ViewHolderBinder<ViewType>(
            parent.inflate(R.layout.item_date_picker)
        ) {
            override fun bind(uiModel: ViewType) {
                itemView.apply {

                }
            }
        }
        //endregion
    }
    //endregion
}