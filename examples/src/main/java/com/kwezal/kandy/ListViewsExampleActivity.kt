package com.kwezal.kandy

import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.kwezal.kandy.listviews.*
import com.kwezal.kandy.listviews.base.AbstractKandyItemView
import com.kwezal.kandy.listviews.base.AbstractKandyListItem
import com.kwezal.kandy.listviews.interfaces.IKandyViewHolderCreator

private const val STRING_VIEW_TYPE = 0
private const val BOOLEAN_VIEW_TYPE = 1

class ListViewsExampleActivity : AppCompatActivity() {
    private val adapter = KandyListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
            KandyListView(this).apply {
                adapter = this@ListViewsExampleActivity.adapter
                layoutManager = LinearLayoutManager(this@ListViewsExampleActivity)
            }
        )

        with(adapter) {
            // Item insertion in a minimum number of subclasses approach
            add(
                KandyListItem(
                    "String item",
                    KandyItemView(STRING_VIEW_TYPE) { TextView(this) }
                ) { itemView -> StringViewHolder(itemView) }
            )
            // Item insertion example
            add(BooleanListItem(false))
            // Creator insertion example
            add { BooleanListItem(true) }
        }
    }

    /**
     * The only class needed in a minimum number of subclasses approach.
     * It displays the given text in a layout containing a single [TextView].
     */
    private class StringViewHolder(itemView: View) :
        AbstractDefaultKandyViewHolder<String>(itemView) {

        private val textView = itemView as TextView

        override fun onBind(
            position: Int,
            adapter: KandyListAdapter,
            listItemGetter: () -> KandyListItem<String>
        ) {
            textView.text = listItemGetter().item
        }
    }

    private class BooleanListItem(item: Boolean) : AbstractKandyListItem<Boolean>(item) {

        override val createViewHolder: IKandyViewHolderCreator
            get() = { itemView -> BooleanViewHolder(itemView) }

        override val itemView: AbstractKandyItemView
            get() = KandyItemView(BOOLEAN_VIEW_TYPE) {
                CheckBox(this).apply {
                    text = "Boolean item"
                    isEnabled = false
                }
            }
    }

    private class BooleanViewHolder(itemView: View) :
        AbstractKandyAdapterViewHolder<BooleanListItem>(itemView) {

        private val checkBox = itemView as CheckBox

        override fun onBind(
            position: Int,
            adapter: KandyListAdapter,
            listItemGetter: () -> BooleanListItem
        ) {
            checkBox.isChecked = listItemGetter().item
        }
    }
}