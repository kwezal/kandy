package com.kwezal.kandy.listviews.base

import androidx.recyclerview.widget.RecyclerView
import com.kwezal.kandy.listviews.KandyListAdapter
import com.kwezal.kandy.listviews.interfaces.IKandyItemViewCreator

/**
 * Base class for every list item view.
 * It stores both function responsible for creating actual visual representation ([createView]) and its type ([type])
 * which is used by [KandyListAdapter] to recycle views properly.
 * ## Possible dangers
 * Every view ought to have its own,
 * unique type but no exception will be thrown when trying to override an existing view type's creator.
 * However, it could result in [AbstractKandyViewHolder.onBind] errors as previous view holder do not have to be compatible with the new view.
 */
abstract class AbstractKandyItemView(
    /**
     * View type.
     * It **ought to be unique for every view**.
     * For more information, see class documentation.
     * @see AbstractKandyItemView
     * @see RecyclerView.Adapter.getItemViewType
     */
    val type: Int = 0
) {
    /**
     * Creates a view for a list item.
     * @see RecyclerView.Adapter.onCreateViewHolder
     */
    abstract val createView: IKandyItemViewCreator
}