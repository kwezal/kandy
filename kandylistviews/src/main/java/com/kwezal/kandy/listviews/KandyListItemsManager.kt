package com.kwezal.kandy.listviews

import android.view.ViewGroup
import com.kwezal.kandy.listviews.interfaces.IKandyItemViewCreator
import com.kwezal.kandy.listviews.interfaces.IKandyViewHolderCreator
import java.util.*
import kotlin.collections.ArrayList

internal class KandyListItemsManager(
    private val items: MutableList<AbstractAnyKandyListItem> = ArrayList(0)
) {
    val size inline get() = items.size

    private val viewTypesMap: MutableMap<Int, Pair<IKandyViewHolderCreator, IKandyItemViewCreator>>

    init {
        viewTypesMap = TreeMap { o1, o2 -> o1.compareTo(o2) }
        updateViewTypeKeys(items)
    }

    operator fun get(index: Int) = items[index]

    inline fun getAll() = items as List<AbstractAnyKandyListItem>

    inline fun getItemViewType(position: Int) = get(position).viewType

    fun createViewHolder(parent: ViewGroup, viewType: Int): AbstractAnyKandyViewHolder {
        val (viewHolderCreator, viewCreator) = viewTypesMap[viewType]!!
        return viewHolderCreator(parent.context.viewCreator())
    }

    fun replace(itemAt: Int, with: AbstractAnyKandyListItem) {
        items[itemAt] = with
        updateViewTypeKey(with)
    }

    fun add(item: AbstractAnyKandyListItem) {
        items.add(item)
        updateViewTypeKey(item)
    }

    fun addAll(items: Iterable<AbstractAnyKandyListItem>) {
        this.items.addAll(items)
        updateViewTypeKeys(items)
    }

    fun add(item: AbstractAnyKandyListItem, position: Int) {
        items.add(position, item)
        updateViewTypeKey(item)
    }

    fun addAll(items: Collection<AbstractAnyKandyListItem>, position: Int) {
        this.items.addAll(position, items)
        updateViewTypeKeys(items)
    }

    fun remove(element: AbstractAnyKandyListItem): Int {
        val idx = items.indexOf(element)
        if (idx != -1) {
            items.removeAt(idx)
        }
        return idx
    }

    inline fun removeFirst(predicate: (listItem: AbstractAnyKandyListItem) -> Boolean): Int {
        val idx = items.indexOfFirst(predicate)
        if (idx != -1) {
            items.removeAt(idx)
        }
        return idx
    }

    inline fun removeAll(predicate: (index: Int, listItem: AbstractAnyKandyListItem) -> Boolean): List<Int> {
        val removedIndices = mutableListOf<Int>()
        items.forEachIndexed { i, listItem ->
            if(predicate(i, listItem)) {
                items.removeAt(i)
                removedIndices += i
            }
        }
        return removedIndices
    }

    inline fun removeAt(position: Int) = items.removeAt(position)

    fun clear(clearViewTypes: Boolean = true) {
        items.clear()
        if (clearViewTypes)
            viewTypesMap.clear()
    }

    fun removeUnusedViewTypes() {
        val unusedKeys = HashSet(viewTypesMap.keys)
        items.forEach { item ->
            if (unusedKeys.remove(item.viewType) && unusedKeys.isEmpty())
                return
        }
        unusedKeys.forEach { key ->
            viewTypesMap.remove(key)
        }
    }

    private inline fun updateViewTypeKeys(items: Iterable<AbstractAnyKandyListItem>) {
        items.forEach { item: AbstractAnyKandyListItem -> updateViewTypeKey(item) }
    }

    private inline fun updateViewTypeKey(item: AbstractAnyKandyListItem) {
        viewTypesMap[item.viewType] = Pair(item.createViewHolder, item.itemView.createView)
    }
}