package com.kwezal.kandy.listviews

import com.kwezal.kandy.listviews.base.AbstractKandyItemView
import com.kwezal.kandy.listviews.base.AbstractKandyListItem
import com.kwezal.kandy.listviews.interfaces.IKandyViewHolderCreator

/**
 * The simplest list item which can be created by passing [createViewHolder] function as an argument.
 * It provides an alternative to creating a subclass of [AbstractKandyListItem].
 * ## Comparison
 * ### KandyListItem way
 * ```
 * val item = KandyListItem("Life, The Universe, and Everything", KandyItemView {View(this)})
 * {itemView: View -> KandyViewHolder<String>(itemView)}
 * ```
 * ### AbstractKandyListItem way
 * ```
 * class MyKandyListItem(item: String) : AbstractKandyListItem<String>(item) {
 *     override val createViewHolder: IKandyViewHolderCreator = {itemView: View -> KandyViewHolder<String>(itemView)}
 *     override val itemView: AbstractKandyItemView = KandyItemView {View(this)}
 * }
 * val item = MyKandyListItem("Life, The Universe, and Everything")
 * ```
 * ## Performance note
 * For huge amount of list items it's a bad idea to make separate [createViewHolder] function for each element.
 * In that case consider sharing the instance or create a subclass of [AbstractKandyListItem].
 * ```
 * val itemView = KandyItemView {View(this)}
 * val createViewHolder: IKandyViewHolderCreator = {itemView: View -> KandyViewHolder<String>(itemView)}
 * val items = ArrayList<KandyListItem<String>>().apply {
 *     for(i in 0 until 100_000)
 *         add(KandyListItem("Item $i", itemView, createViewHolder))
 * }
 * ```
 * ## Final note
 * This class is closed by purpose.
 * The main idea here is to avoid class creation.
 * If some custom behavior is needed, extend [AbstractKandyListItem] instead.
 * @see MutableKandyListItem
 */
class KandyListItem<ItemType>(
    item: ItemType,
    override val itemView: AbstractKandyItemView,
    override val createViewHolder: IKandyViewHolderCreator
) : AbstractKandyListItem<ItemType>(item)