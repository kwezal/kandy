package com.kwezal.kandy.listviews.base

/**
 * [AbstractKandyListItem] mutable analogue whose item can change the reference.
 * It is useful if the model is a primitive type or there are other reasons for the item to change the reference at runtime.
 */
abstract class AbstractMutableKandyListItem<ItemType>(override var item: ItemType) :
    AbstractKandyListItem<ItemType>(item)