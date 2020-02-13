package com.kwezal.kandy.dialogs

import android.content.Context
import android.content.DialogInterface
import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog

typealias DialogInitializer = (AlertDialog.Builder).() -> Unit

/**
 * The wrapper created to keep the interface and scope of [Context] as clean as possible.
 * Its main purpose is to provide a simple, DSL-like syntax for creating and displaying dialog boxes.
 * An instance of this class can be created only implicitly, through [Context.create] or [Context.show] methods.
 */
class DialogContext private constructor(val ctx: Context) {
    private var positiveButtonOnClick: DialogOnClick? = null
    private var neutralButtonOnClick: DialogOnClick? = null
    private var negativeButtonOnClick: DialogOnClick? = null

    private inline fun AlertDialog.applyPositiveButtonOnClick() {
        val onClick = positiveButtonOnClick ?: return
        positiveButton?.setOnClickListener { onClick(this, DialogInterface.BUTTON_POSITIVE) }
    }

    private inline fun AlertDialog.applyNeutralButtonOnClick() {
        val onClick = neutralButtonOnClick ?: return
        neutralButton?.setOnClickListener { onClick(this, DialogInterface.BUTTON_NEUTRAL) }
    }

    private inline fun AlertDialog.applyNegativeButtonOnClick() {
        val onClick = negativeButtonOnClick ?: return
        negativeButton?.setOnClickListener { onClick(this, DialogInterface.BUTTON_NEGATIVE) }
    }

    internal inline fun AlertDialog.applyButtonsOnClick() {
        applyPositiveButtonOnClick()
        applyNeutralButtonOnClick()
        applyNegativeButtonOnClick()
    }

    inline fun button(
        text: CharSequence,
        icon: Drawable? = null,
        noinline onClick: DialogOnClick = { }
    ) = DialogButton(text, icon, onClick)

    inline fun button(
        @StringRes textResource: Int,
        icon: Drawable? = null,
        noinline onClick: DialogOnClick = { }
    ) = DialogButton(ctx, textResource, icon, onClick)

    inline fun button(
        text: CharSequence,
        @DrawableRes iconResource: Int,
        noinline onClick: DialogOnClick = { }
    ) = DialogButton(ctx, text, iconResource, onClick)

    inline fun button(
        @StringRes textResource: Int,
        @DrawableRes iconResource: Int,
        noinline onClick: DialogOnClick = { }
    ) = DialogButton(ctx, textResource, iconResource, onClick)

    /**
     * Creates an instance of [AlertDialog.Builder] using the given [init] function.
     * @param init a function that prepares the builder to create a real dialog
     */
    inline fun dialog(init: DialogInitializer) = AlertDialog.Builder(ctx).apply { init() }

    /**
     * @see dialog
     */
    inline fun dialog(message: CharSequence? = null, title: CharSequence? = null, init: DialogInitializer = {}) =
        dialog {
            this.title = title
            this.message = message
            init()
        }

    /**
     * @see dialog
     */
    inline fun dialog(@StringRes messageResource: Int, @StringRes titleResource: Int, init: DialogInitializer = {}) =
        dialog {
            this.titleResource = titleResource
            this.messageResource = messageResource
            init()
        }

    /**
     * @see dialog
     */
    inline fun customViewDialog(view: View, init: DialogInitializer = {}) = dialog {
        this.view = view
        init()
    }

    /**
     * @see dialog
     */
    inline fun customViewDialog(@LayoutRes viewResource: Int, init: DialogInitializer = {}) = dialog {
        this.viewResource = viewResource
        init()
    }

    internal companion object {
        fun create(context: Context) = DialogContext(context)
    }

    /**
     * Creates the positive button using the given text, icon and click listener.
     * ## Note
     * This field is the only way to make use of `autoDismiss` parameter of [Context.create] and [Context.show] methods.
     * @see AlertDialog.Builder.positiveButton
     */
    var AlertDialog.Builder.positiveButton: DialogButton
        get() = throw UnsupportedOperationException()
        set(value) {
            positiveButtonOnClick = value.onClicked
            positiveButton(value.text, value.icon, value.onClicked)
        }

    /**
     * Creates the positive button using the given text, icon and click listener.
     * ## Note
     * This field is the only way to make use of `autoDismiss` parameter of [Context.create] and [Context.show] methods.
     * @see AlertDialog.Builder.neutralButton
     */
    var AlertDialog.Builder.neutralButton: DialogButton
        get() = throw UnsupportedOperationException()
        set(value) {
            neutralButtonOnClick = value.onClicked
            neutralButton(value.text, value.icon, value.onClicked)
        }

    /**
     * Creates the positive button using the given text, icon and click listener.
     * ## Note
     * This field is the only way to make use of `autoDismiss` parameter of [Context.create] and [Context.show] methods.
     * @see AlertDialog.Builder.negativeButton
     */
    var AlertDialog.Builder.negativeButton: DialogButton
        get() = throw UnsupportedOperationException()
        set(value) {
            negativeButtonOnClick = value.onClicked
            negativeButton(value.text, value.icon, value.onClicked)
        }
}