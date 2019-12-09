package com.kwezal.kandy.listviews

import com.kwezal.kandy.listviews.base.AbstractKandyItemView
import com.kwezal.kandy.listviews.base.AbstractMutableKandyListItem
import com.kwezal.kandy.listviews.interfaces.IKandyViewHolderCreator

/**
 * [KandyListItem] mutable analogue whose item can change the reference.
 * It is useful if the model is a primitive type or there are other reasons for the item to change the reference at runtime.
 */
class MutableKandyListItem<ItemType>(
    item: ItemType,
    override val itemView: AbstractKandyItemView,
    override val createViewHolder: IKandyViewHolderCreator
) : AbstractMutableKandyListItem<ItemType>(item)