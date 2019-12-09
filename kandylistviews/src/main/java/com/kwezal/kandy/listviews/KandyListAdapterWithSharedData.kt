package com.kwezal.kandy.listviews

import com.kwezal.kandy.listviews.base.AbstractKandyViewHolder

/**
 * Adapter which contains data shared with all list items.
 * The data can be accessed via adapter argument in [AbstractKandyViewHolder.onBind] method.
 * Instead of creating fields for each view holder it's more clean, efficient and less memory-intensive to store shared data
 * (e.g. activity reference or bitmaps) within adapter itself.
 */
open class KandyListAdapterWithSharedData<DataType>(
    val sharedData: DataType,
    items: MutableList<out AbstractAnyKandyListItem> = ArrayList(0)
) : KandyListAdapter(items)