package com.kwezal.kandy

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import splitties.views.dsl.core.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ui)
    }

    private val ui
        get() = object : Ui {
            override val ctx: Context
                get() = this@MainActivity

            override val root: View
                get() = verticalLayout {
                    add(
                        createNavigationButton(
                            R.string.activity_label_list_views,
                            ListViewsExampleActivity::class.java
                        ), lParams(matchParent)
                    )
                }

            private fun createNavigationButton(text: Int, activityClass: Class<out Activity>) =
                button {
                    setText(text)
                    setOnClickListener {ctx.startActivity(Intent(ctx, activityClass))}
                }
        }
}