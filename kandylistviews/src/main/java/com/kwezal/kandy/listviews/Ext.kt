@file:JvmName("Extensions")
@file:JvmMultifileClass

package com.kwezal.kandy.listviews

import com.kwezal.kandy.listviews.base.AbstractKandyListItem
import com.kwezal.kandy.listviews.base.AbstractKandyViewHolder
import com.kwezal.kandy.listviews.interfaces.IKandyListItemCreator

/**
 * [AbstractKandyViewHolder] with adapter type set to [KandyListAdapter].
 */
typealias AbstractKandyAdapterViewHolder<ListItemType> = AbstractKandyViewHolder<KandyListAdapter, ListItemType>

/**
 * [AbstractKandyViewHolder] with adapter type set to [KandyListAdapter] and list item type set to [KandyListItem]
 */
typealias AbstractDefaultKandyViewHolder<ItemType> = AbstractKandyAdapterViewHolder<KandyListItem<ItemType>>

/**
 * [AbstractKandyViewHolder] without any information about types of adapter and list item.
 */
typealias AbstractAnyKandyViewHolder = AbstractKandyViewHolder<*, *>

/**
 * [AbstractKandyListItem] without any information about item type.
 */
typealias AbstractAnyKandyListItem = AbstractKandyListItem<*>

inline fun Iterable<IKandyListItemCreator>.createListItems() = mapTo(ArrayList(count())) {creator -> creator()}