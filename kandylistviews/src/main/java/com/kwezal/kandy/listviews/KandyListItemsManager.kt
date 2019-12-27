package com.kwezal.kandy.listviews

import android.view.ViewGroup
import com.kwezal.kandy.listviews.interfaces.IKandyItemViewCreator
import com.kwezal.kandy.listviews.interfaces.IKandyViewHolderCreator
import java.util.*
import kotlin.collections.ArrayList

internal class KandyListItemsManager(items: MutableList<AbstractAnyKandyListItem> = ArrayList(0)) {
    private val mItems = items
    val size inline get() = mItems.size

    private val mViewTypesMap: MutableMap<Int, Pair<IKandyViewHolderCreator, IKandyItemViewCreator>>

    init {
        mViewTypesMap = TreeMap { o1, o2 -> o1.compareTo(o2) }
        updateViewTypeKeys(items)
    }

    operator fun get(index: Int) = mItems[index]

    inline fun getAll() = mItems as List<AbstractAnyKandyListItem>

    inline fun getItemViewType(position: Int) = get(position).viewType

    fun createViewHolder(parent: ViewGroup, viewType: Int): AbstractAnyKandyViewHolder {
        val (viewHolderCreator, viewCreator) = mViewTypesMap[viewType]!!
        return viewHolderCreator(parent.context.viewCreator())
    }

    fun replace(itemAt: Int, with: AbstractAnyKandyListItem) {
        mItems[itemAt] = with
        updateViewTypeKey(with)
    }

    fun add(item: AbstractAnyKandyListItem) {
        mItems.add(item)
        updateViewTypeKey(item)
    }

    fun addAll(items: Iterable<AbstractAnyKandyListItem>) {
        mItems.addAll(items)
        updateViewTypeKeys(items)
    }

    fun add(item: AbstractAnyKandyListItem, position: Int) {
        mItems.add(position, item)
        updateViewTypeKey(item)
    }

    fun addAll(items: Collection<AbstractAnyKandyListItem>, position: Int) {
        mItems.addAll(position, items)
        updateViewTypeKeys(items)
    }

    inline fun removeAt(position: Int) {
        mItems.removeAt(position)
    }

    fun clear(clearViewTypes: Boolean = true) {
        mItems.clear()
        if (clearViewTypes)
            mViewTypesMap.clear()
    }

    fun removeUnusedViewTypes() {
        val unusedKeys = HashSet(mViewTypesMap.keys)
        mItems.forEach { item ->
            if (unusedKeys.remove(item.viewType) && unusedKeys.isEmpty())
                return
        }
        unusedKeys.forEach { key ->
            mViewTypesMap.remove(key)
        }
    }

    private inline fun updateViewTypeKeys(items: Iterable<AbstractAnyKandyListItem>) {
        items.forEach { item: AbstractAnyKandyListItem -> updateViewTypeKey(item) }
    }

    private inline fun updateViewTypeKey(item: AbstractAnyKandyListItem) {
        mViewTypesMap[item.viewType] = Pair(item.createViewHolder, item.itemView.createView)
    }
}