package com.kwezal.kandy

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.kwezal.kandy.dialogs.*
import splitties.views.dsl.core.*

class DialogsExampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ui)

        show {
            dialog("Welcome to the Dialogs Example Activity!", "Greetings!") {
                positiveButton("Thanks")
                negativeButton("I'm outta here") { finish() }
            }
        }
    }

    private inline val ui
        get() = object : Ui {
            override val ctx: Context
                get() = this@DialogsExampleActivity

            override val root: View
                get() = verticalLayout {

                    addDialogButton("Two dialogs") {
                        // Show the dialog in a manual dismiss mode
                        show(false) {
                            dialog {
                                title = "First dialog"
                                message = "Would you like to open second dialog?"

                                // Open a new dialog, but don't close the previous one
                                positiveButton("Definitely!") {
                                    show {
                                        dialog(
                                            "I'm very glad you wanted to see me!",
                                            "Second dialog"
                                        ) {
                                            positiveButton("The pleasure is mine")
                                        }
                                    }
                                }
                                // Do nothing on click
                                neutralButton("Let me think")
                                // Close the dialog on click
                                negativeButton("No, it's enough") { dismiss() }
                            }
                        }

                    }
                    // Show beautiful dialog full of icons
                    addDialogButton("Icons") {
                        show {
                            dialog {
                                title = "Title"
                                iconResource = R.mipmap.ic_launcher
                                isCancelable = false

                                positiveButton("Positive", R.mipmap.ic_launcher)
                                neutralButton("Neutral", R.mipmap.ic_launcher)
                                negativeButton("Negative", R.mipmap.ic_launcher)
                            }
                        }
                    }

                    addDialogButton("Custom check box view") {
                        show {
                            customViewDialog(CheckBox(ctx).apply {
                                text = "Useless check box"
                            })
                        }
                    }

                    addDialogButton("Custom button view") {
                        show {
                            dialog {
                                view = Button(ctx).apply { text = "Useless button" }
                            }
                        }
                    }
                    // Show empty dialog
                    addDialogButton("Turn off the light") { show { dialog {} } }
                    // Show the dialog using string resources only #1
                    addDialogButton("Exit") {
                        show {
                            dialog(
                                R.string.dialog_message_exit_confirmation,
                                R.string.dialog_title_exit_confirmation
                            ) {
                                positiveButton(R.string.exit) { finish() }
                                negativeButton(R.string.stay)
                            }
                        }
                    }
                    // Show the dialog using string resources only #2
                    addDialogButton("Alternative exit") {
                        show {
                            dialog {
                                titleResource = R.string.dialog_title_exit_confirmation
                                messageResource = R.string.dialog_message_exit_confirmation

                                positiveButton(R.string.exit) { finish() }
                                negativeButton(R.string.stay)
                            }
                        }
                    }
                }

            private fun LinearLayout.addDialogButton(
                text: CharSequence,
                dialog: () -> AlertDialog
            ) = add(
                button {
                    setText(text)
                    setOnClickListener { dialog() }
                }, lParams(matchParent)
            )
        }
}