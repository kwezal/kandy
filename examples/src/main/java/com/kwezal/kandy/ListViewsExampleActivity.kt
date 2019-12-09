package com.kwezal.kandy

import android.content.Context
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
import splitties.views.dsl.core.Ui
import splitties.views.dsl.core.setContentView

private const val STRING_VIEW_TYPE = 0
private const val BOOLEAN_VIEW_TYPE = 1

class ListViewsExampleActivity : AppCompatActivity() {
    private val adapter = KandyListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ui)

        with(adapter) {

            insertCreator {
                KandyListItem(
                    "String item",
                    KandyItemView(STRING_VIEW_TYPE) {TextView(this)}
                ) {itemView -> StringViewHolder(itemView)}
            }

            insertItem(BooleanListItem(false))

            insertItem(BooleanListItem(true))
        }
    }

    private val ui
        get() = object : Ui {

            override val ctx: Context
                get() = this@ListViewsExampleActivity

            override val root: View
                get() = KandyListView(ctx).apply {
                    adapter = this@ListViewsExampleActivity.adapter
                    layoutManager = LinearLayoutManager(ctx)
                }
        }

    companion object {

        private class StringViewHolder(itemView: View) : AbstractDefaultKandyViewHolder<String>(itemView) {

            private val textView = itemView as TextView

            override fun onBind(position: Int, adapter: KandyListAdapter, listItemGetter: () -> KandyListItem<String>) {
                textView.text = listItemGetter().item
            }
        }

        private class BooleanListItem(item: Boolean) : AbstractKandyListItem<Boolean>(item) {

            override val createViewHolder: IKandyViewHolderCreator
                get() = {itemView -> BooleanViewHolder(itemView)}

            override val itemView: AbstractKandyItemView
                get() = KandyItemView(BOOLEAN_VIEW_TYPE) {
                    CheckBox(this).apply {
                        text = "Boolean item"
                        isEnabled = false
                    }
                }
        }

        private class BooleanViewHolder(itemView: View) : AbstractKandyAdapterViewHolder<BooleanListItem>(itemView) {

            private val checkBox = itemView as CheckBox

            override fun onBind(position: Int, adapter: KandyListAdapter, listItemGetter: () -> BooleanListItem) {
                with(checkBox) {
                    isChecked = listItemGetter().item
                }
            }
        }
    }
}