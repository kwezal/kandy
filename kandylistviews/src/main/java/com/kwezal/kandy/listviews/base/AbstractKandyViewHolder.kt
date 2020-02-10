package com.kwezal.kandy.listviews.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.kwezal.kandy.listviews.*

/**
 * Base class for every view holder used in [KandyListView].
 * The same as [RecyclerView.ViewHolder], it describes an item view and metadata about its place within the KandyListView.
 * Moreover, it provides its own [onBind] method (in contrast to centralized [RecyclerView.Adapter.onBindViewHolder] approach.)
 * @param AdapterType A custom adapter type.
 * If [KandyListAdapter] is used, consider using [AbstractKandyAdapterViewHolder] type alias instead.
 * @param ListItemType A class that represents the list item with which the view holder is bound.
 * @see AbstractAnyKandyViewHolder
 */
abstract class AbstractKandyViewHolder<AdapterType : KandyListAdapter, ListItemType : AbstractAnyKandyListItem>(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {
    /**
     * Called by [KandyListView] to display the data at the specified position.
     * It takes a getter function (instead of a simple reference) as an argument in order to be able to retrieve
     * the proper list item within callbacks, event listeners etc.
     * ## Example
     * ```
     * override fun onBind(
     *     position: Int,
     *     adapter: KandyListAdapter,
     *     listItemGetter: () -> AbstractMutableKandyListItem<Boolean>
     * ) {
     *     val itemIsChecked = listItemGetter().item
     *     with(checkBox) {
     *         isChecked = itemIsChecked // item can be safely used here
     *         setOnCheckedChangeListener { _, isChecked ->
     *             // item should be retrieved again here as this code will not happen immediately
     *             listItemGetter().item = isChecked
     *         }
     *     }
     * }
     * ```
     * @param position The position of the item within the adapter's data set
     * @param adapter The reference to the adapter which called this method
     * @param listItemGetter A getter function to retrieve the list item with which the view holder is bound
     * @see RecyclerView.Adapter.onBindViewHolder
     */
    abstract fun onBind(position: Int, adapter: AdapterType, listItemGetter: () -> ListItemType)

    @Suppress("UNCHECKED_CAST") // If the library is used correctly, it is a safe cast
    internal inline fun delegateOnBind(position: Int, adapter: KandyListAdapter, noinline listItemGetter: () -> Any?) {
        onBind(position, adapter as AdapterType, listItemGetter as () -> ListItemType)
    }
}