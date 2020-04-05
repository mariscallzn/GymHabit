package com.andymariscal.shared.inf

import android.util.SparseArray
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.andymariscal.shared.R
import com.andymariscal.shared.utils.inflate
import kotlinx.android.synthetic.main.item_simple_string.view.*

//region Constants
const val STRING_VIEW_TYPE = 1
//endregion

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

//region ViewHolderBinder
abstract class ViewHolderBinder<in T>(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(uiModel: T)
}
//endregion

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

//region OpenViewType
abstract class OpenViewType(private val viewTypeId: Int = STRING_VIEW_TYPE): ViewType {
    override fun getViewType(): Int = viewTypeId
}
//endregion

// region DefaultDiffCallback
object DefaultDiffCallback : DiffUtil.ItemCallback<ViewType>() {
    override fun areItemsTheSame(oldItem: ViewType, newItem: ViewType): Boolean =
        oldItem.getUniqueProperty() == newItem.getUniqueProperty()

    override fun areContentsTheSame(oldItem: ViewType, newItem: ViewType): Boolean =
        oldItem.hashCode() == newItem.hashCode()
}
// endregion

//region String Converter
object StringConverter : IConverter<String, ViewType> {
    override fun convert(source: String): ViewType =
        object : ViewType {
            override fun getViewType(): Int = STRING_VIEW_TYPE
            override fun getUniqueProperty(): Any = source
        }
}
//endregion