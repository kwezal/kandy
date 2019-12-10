package com.kwezal.kandy.listviews

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kwezal.kandy.listviews.interfaces.IKandyListItemCreator

/**
 * Highly flexible adapter for the [RecyclerView].
 * It manages the list view by generating appropriate behavior based on the given list items.
 * The adapter overrides [getItemCount], [getItemViewType], [onCreateViewHolder] and [onBindViewHolder] methods,
 * so in most cases there is no need to create a subclass.
 * @param items Initial list of items
 * @see KandyListAdapterWithSharedData
 */
open class KandyListAdapter
    (
    items: MutableList<out AbstractAnyKandyListItem>
) : RecyclerView.Adapter<AbstractAnyKandyViewHolder>() {
    private val mItemsManager = KandyListItemsManager(items as MutableList<AbstractAnyKandyListItem>)

    /**
     * All list items.
     */
    val items get() = mItemsManager.getAll()

    /**
     * Creates empty list adapter.
     */
    constructor() : this(ArrayList<AbstractAnyKandyListItem>(0))

    constructor(items: Iterable<IKandyListItemCreator>) : this(items.createListItems())
    constructor(vararg items: AbstractAnyKandyListItem) : this(mutableListOf(*items))
    constructor(vararg items: IKandyListItemCreator) : this(listOf(*items))

    final override fun getItemCount() = mItemsManager.size

    final override fun getItemViewType(position: Int) = mItemsManager.getItemViewType(position)

    final override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        mItemsManager.createViewHolder(parent, viewType)

    final override fun onBindViewHolder(holder: AbstractAnyKandyViewHolder, position: Int) {
        holder.delegateOnBind(position, this) {mItemsManager[holder.adapterPosition]}
    }

    fun replace(itemAt: Int, with: AbstractAnyKandyListItem) {
        mItemsManager.replace(itemAt, with)
        notifyItemChanged(itemAt)
    }

    fun insertItem(item: AbstractAnyKandyListItem) {
        mItemsManager.add(item)
        notifyItemInserted(mItemsManager.size - 1)
    }

    inline fun insertCreator(creator: IKandyListItemCreator) {
        insertItem(creator())
    }

    fun insertItems(items: Iterable<AbstractAnyKandyListItem>) {
        mItemsManager.addAll(items)
        notifyItemRangeInserted(mItemsManager.size - 1, items.count())
    }

    inline fun insertCreators(creators: Iterable<IKandyListItemCreator>) {
        insertItems(creators.map {creator -> creator()})
    }

    fun insertItem(position: Int, item: AbstractAnyKandyListItem) {
        mItemsManager.add(item, position)
        notifyItemInserted(position)
    }

    inline fun insertCreator(position: Int, creator: IKandyListItemCreator) {
        insertItem(position, creator())
    }

    fun insertItems(position: Int, items: Collection<AbstractAnyKandyListItem>) {
        mItemsManager.addAll(items, position)
        notifyItemRangeInserted(position, items.size)
    }

    inline fun insertCreators(position: Int, creators: Collection<IKandyListItemCreator>) {
        insertItems(position, creators.map {creator -> creator()})
    }

    fun removeAt(position: Int) {
        mItemsManager.removeAt(position)
        notifyItemRemoved(position)
    }

    /**
     * Removes all list items.
     * @param clearViewTypes If set to `true`, information about all cached view types will be removed too
     */
    fun removeAll(clearViewTypes: Boolean = true) {
        val size = mItemsManager.size
        mItemsManager.clear(clearViewTypes)
        notifyItemRangeRemoved(0, size)
    }

    /**
     * Removes information about unused view types.
     * By default, this information is kept even after removal of last item of some view type.
     * This method can be useful if a lot of view types generation and list items removal/replacement takes place.
     * Otherwise, the impact on performance and memory will be insignificant.
     */
    fun removeUnusedViewTypes() = mItemsManager.removeUnusedViewTypes()
}