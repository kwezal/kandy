package com.kwezal.kandy.listviews.base

import android.view.View
import com.kwezal.kandy.listviews.AbstractAnyKandyViewHolder
import com.kwezal.kandy.listviews.interfaces.IKandyViewHolderCreator

/**
 * Base class for every list item displayable in [KandyListView].
 * @param item An object whose data will be displayed in a list item
 * @param ItemType A class (model) that represents list item data
 */
abstract class AbstractKandyListItem<ItemType>(open val item: ItemType) {
    /**
     * Item view type.
     */
    val viewType inline get() = itemView.type

    /**
     * A function used to create view holder lazily.
     */
    abstract val createViewHolder: IKandyViewHolderCreator

    /**
     * An item view of list item.
     */
    abstract val itemView: AbstractKandyItemView

    /**
     * Creates [AbstractAnyKandyViewHolder] using [createViewHolder] function.
     * @see createViewHolder
     */
    inline fun createItemViewHolder(view: View) = createViewHolder(view)
}