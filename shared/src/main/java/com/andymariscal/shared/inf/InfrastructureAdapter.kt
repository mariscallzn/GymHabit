package com.andymariscal.shared.inf

import android.util.SparseArray
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

// region ViewTypeDelegate typealias
typealias ViewTypeDelegate = Pair<Int, ViewTypeDelegateAdapter>
// endregion

// region ItemHandlerAdapter
class ItemDelegateAdapter(
    private val builder: Builder
) : ListAdapter<ViewType, RecyclerView.ViewHolder>(builder.itemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        builder.delegates[viewType].onCreateViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        builder.delegates[getItemViewType(position)].onBindViewHolder(holder, getItem(position))

    override fun getItemViewType(position: Int): Int =
        getItem(position).getViewType()

    class Builder(
        internal val itemCallback: DiffUtil.ItemCallback<ViewType> = DefaultDiffCallback
    ) {
        internal val delegates = SparseArray<ViewTypeDelegateAdapter>()

        fun addAllDelegates(vararg viewTypeDelegates: ViewTypeDelegate): Builder = this.apply {
            viewTypeDelegates.forEach { viewDelegate ->
                delegates.append(viewDelegate.first, viewDelegate.second)
            }
        }
    }
}
// endregion

// region ViewTypeDelegateAdapter
abstract class ViewTypeDelegateAdapter {
    abstract fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder
    abstract fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder?, viewType: ViewType)
}
// endregion

// region ViewType interface
interface ViewType {
    fun getViewType(): Int
    fun getUniqueProperty(): Any
}
// endregion

// region DefaultDiffCallback
object DefaultDiffCallback : DiffUtil.ItemCallback<ViewType>() {
    override fun areItemsTheSame(oldItem: ViewType, newItem: ViewType): Boolean =
        oldItem.getUniqueProperty() == newItem.getUniqueProperty()

    override fun areContentsTheSame(oldItem: ViewType, newItem: ViewType): Boolean =
        oldItem.hashCode() == newItem.hashCode()
}
// endregion