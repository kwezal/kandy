package com.kwezal.kandy.listviews

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView

/**
 * A subclass of [RecyclerView] that is adjusted to support [KandyListAdapter].
 * It has overridden [getAdapter] and [setAdapter] methods so they are type safe.
 */
class KandyListView(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : RecyclerView(context, attrs, defStyle) {
    var adapter: KandyListAdapter?
        @JvmName(name = "getKandyAdapter") inline get() = getAdapter()
        inline set(value) {
            setAdapter(value)
        }

    override inline fun getAdapter(): KandyListAdapter? {
        return super.getAdapter() as KandyListAdapter?
    }

    /**
     * @throws IllegalArgumentException If [adapter] is not a subtype of [KandyListAdapter]
     * @see RecyclerView.setAdapter
     */
    override fun setAdapter(adapter: Adapter<*>?) {
        require(adapter is KandyListAdapter?) {"Adapter is not a subtype of KandyListAdapter"}
        super.setAdapter(adapter)
    }
}