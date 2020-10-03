package com.kwezal.kandy.listviews

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kwezal.kandy.listviews.interfaces.IKandyListItemCreator

/**
 * Highly flexible adapter for the [RecyclerView].
 * It manages the list view by generating appropriate behavior based on the given list items.
 * The adapter overrides [getItemCount], [getItemViewType], [onCreateViewHolder] and [onBindViewHolder] methods,
 * so in most cases, there is no need to create a subclass.
 * @param items Initial list of items
 * @see KandyListAdapterWithSharedData
 */
open class KandyListAdapter(
    items: MutableList<out AbstractAnyKandyListItem>
) : RecyclerView.Adapter<AbstractAnyKandyViewHolder>() {

    private val itemsManager = KandyListItemsManager(items as MutableList<AbstractAnyKandyListItem>)

    /**
     * All list items.
     */
    val items get() = itemsManager.getAll()

    /**
     * Creates empty list adapter.
     */
    constructor() : this(ArrayList<AbstractAnyKandyListItem>(0))

    constructor(items: Iterable<IKandyListItemCreator>) : this(items.createListItems())
    constructor(vararg items: AbstractAnyKandyListItem) : this(mutableListOf(*items))
    constructor(vararg items: IKandyListItemCreator) : this(listOf(*items))

    final override fun getItemCount() = itemsManager.size

    final override fun getItemViewType(position: Int) = itemsManager.getItemViewType(position)

    final override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        itemsManager.createViewHolder(parent, viewType)

    final override fun onBindViewHolder(holder: AbstractAnyKandyViewHolder, position: Int) {
        holder.delegateOnBind(position, this) { itemsManager[holder.adapterPosition] }
    }

    fun replace(itemAt: Int, with: AbstractAnyKandyListItem) {
        itemsManager.replace(itemAt, with)
        notifyItemChanged(itemAt)
    }

    fun add(item: AbstractAnyKandyListItem) {
        itemsManager.add(item)
        notifyItemInserted(itemsManager.size - 1)
    }

    inline fun add(creator: IKandyListItemCreator) {
        add(creator())
    }

    @JvmName(name = "addItems")
    fun add(items: Iterable<AbstractAnyKandyListItem>) {
        itemsManager.addAll(items)
        notifyItemRangeInserted(itemsManager.size - 1, items.count())
    }

    @JvmName(name = "addCreators")
    inline fun add(creators: Iterable<IKandyListItemCreator>) {
        add(creators.map { creator -> creator() })
    }

    fun add(position: Int, item: AbstractAnyKandyListItem) {
        itemsManager.add(item, position)
        notifyItemInserted(position)
    }

    inline fun add(position: Int, creator: IKandyListItemCreator) {
        add(position, creator())
    }

    @JvmName(name = "addItems")
    fun add(position: Int, items: Collection<AbstractAnyKandyListItem>) {
        itemsManager.addAll(items, position)
        notifyItemRangeInserted(position, items.size)
    }

    @JvmName(name = "addCreators")
    inline fun add(position: Int, creators: Collection<IKandyListItemCreator>) {
        add(position, creators.map { creator -> creator() })
    }

    fun removeAt(position: Int) {
        itemsManager.removeAt(position)
        notifyItemRemoved(position)
    }

    /**
     * Removes all list items.
     * @param clearViewTypes If set to `true`, information about all cached view types will be removed too
     */
    fun removeAll(clearViewTypes: Boolean = true) {
        val size = itemsManager.size
        itemsManager.clear(clearViewTypes)
        notifyItemRangeRemoved(0, size)
    }

    /**
     * Removes information about unused view types.
     * By default, this information is kept even after removal of last item of some view type.
     * This method can be useful if a lot of view types generation and list items removal/replacement takes place.
     * Otherwise, the impact on performance and memory will be insignificant.
     */
    fun removeUnusedViewTypes() = itemsManager.removeUnusedViewTypes()

    @Deprecated("This method will be removed in the future", ReplaceWith("add(item)"))
    inline fun insertItem(item: AbstractAnyKandyListItem) {
        add(item)
    }

    @Deprecated("This method will be removed in the future", ReplaceWith("add(creator)"))
    inline fun insertCreator(creator: IKandyListItemCreator) {
        add(creator)
    }

    @Deprecated("This method will be removed in the future", ReplaceWith("add(items)"))
    inline fun insertItems(items: Iterable<AbstractAnyKandyListItem>) {
        add(items)
    }

    @Deprecated("This method will be removed in the future", ReplaceWith("add(creators)"))
    inline fun insertCreators(creators: Iterable<IKandyListItemCreator>) {
        add(creators)
    }

    @Deprecated("This method will be removed in the future", ReplaceWith("add(position, item)"))
    inline fun insertItem(position: Int, item: AbstractAnyKandyListItem) {
        add(position, item)
    }

    @Deprecated("This method will be removed in the future", ReplaceWith("add(position, creator)"))
    inline fun insertCreator(position: Int, creator: IKandyListItemCreator) {
        add(position, creator)
    }

    @Deprecated("This method will be removed in the future", ReplaceWith("add(position, items)"))
    inline fun insertItems(position: Int, items: Collection<AbstractAnyKandyListItem>) {
        add(position, items)
    }

    @Deprecated("This method will be removed in the future", ReplaceWith("add(position, creators)"))
    inline fun insertCreators(position: Int, creators: Collection<IKandyListItemCreator>) {
        add(position, creators)
    }
}