@file:JvmName("Extensions")
@file:JvmMultifileClass

package com.kwezal.kandy.dialogs

import android.content.Context
import android.content.DialogInterface
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat

/**
 * A function that operates on [DialogContext] methods to initialize the dialog components.
 */
typealias DialogBuilder = DialogContext.() -> AlertDialog.Builder

/**
 * Creates the dialog without displaying it.
 * It can be later shown by [AlertDialog.show] method.
 * @param autoDismiss if set to `true`, all buttons will dismiss the dialog on click by default;
 * if set to `false`, the [DialogInterface.dismiss] method will have to be invoked explicitly
 * @param dialogBuilder a function that initializes the dialog components
 * @see DialogBuilder
 * @see show
 */
@JvmName(name = "createDialog")
fun Context.create(autoDismiss: Boolean = true, dialogBuilder: DialogBuilder): AlertDialog =
    DialogContext.create(this).run {
        dialogBuilder().create().apply {
            if (!autoDismiss)
                setOnShowListener { applyButtonsOnClick() }
        }
    }

/**
 * Creates and displays the dialog.
 * @see DialogBuilder
 * @see create
 */
@JvmName(name = "showDialog")
inline fun Context.show(autoDismiss: Boolean = true, noinline dialogBuilder: DialogBuilder): AlertDialog =
    create(autoDismiss, dialogBuilder).apply { show() }

val AlertDialog.positiveButton: Button? inline get() = getButton(DialogInterface.BUTTON_POSITIVE)
val AlertDialog.neutralButton: Button? inline get() = getButton(DialogInterface.BUTTON_NEUTRAL)
val AlertDialog.negativeButton: Button? inline get() = getButton(DialogInterface.BUTTON_NEGATIVE)

/**
 * Creates the positive button using the given text, icon and click listener.
 * ## Note
 * This method is [DialogContext]-independent,
 * which means that the `autoDismiss` parameter of [create] and [show] methods won't have any effect on this button.
 * If you would like to make use of that parameter, consider using [DialogContext.positiveButton] instead.
 * @see AlertDialog.Builder.setPositiveButton
 * @see AlertDialog.Builder.setPositiveButtonIcon
 */
inline fun AlertDialog.Builder.positiveButton(
    text: CharSequence? = null,
    icon: Drawable? = null,
    crossinline onClicked: DialogOnClick = {}
) {
    setPositiveButton(text) { dialog, which -> dialog.onClicked(which) }
    icon?.let { setPositiveButtonIcon(it) }
}

/**
 * @see AlertDialog.Builder.positiveButton
 */
inline fun AlertDialog.Builder.positiveButton(
    @StringRes textResource: Int,
    icon: Drawable? = null,
    crossinline onClicked: DialogOnClick = {}
) {
    positiveButton(context.getString(textResource), icon, onClicked)
}

/**
 * @see AlertDialog.Builder.positiveButton
 */
inline fun AlertDialog.Builder.positiveButton(
    text: CharSequence? = null,
    @DrawableRes iconResource: Int,
    crossinline onClicked: DialogOnClick = {}
) {
    positiveButton(text, ContextCompat.getDrawable(context, iconResource), onClicked)
}

/**
 * @see AlertDialog.Builder.positiveButton
 */
inline fun AlertDialog.Builder.positiveButton(
    @StringRes textResource: Int,
    @DrawableRes iconResource: Int,
    crossinline onClicked: DialogOnClick = {}
) {
    positiveButton(context.getString(textResource), ContextCompat.getDrawable(context, iconResource), onClicked)
}

/**
 * Creates the neutral button using the given text, icon and click listener.
 * ## Note
 * This method is [DialogContext]-independent,
 * which means that the `autoDismiss` parameter of [create] and [show] methods won't have any effect on this button.
 * If you would like to make use of that parameter, consider using [DialogContext.neutralButton] instead.
 * @see AlertDialog.Builder.setNeutralButton
 * @see AlertDialog.Builder.setNeutralButtonIcon
 */
inline fun AlertDialog.Builder.neutralButton(
    text: CharSequence? = null,
    icon: Drawable? = null,
    crossinline onClicked: DialogOnClick = {}
) {
    setNeutralButton(text) { dialog, which -> dialog.onClicked(which) }
    icon?.let { setNeutralButtonIcon(it) }
}

/**
 * @see AlertDialog.Builder.neutralButton
 */
inline fun AlertDialog.Builder.neutralButton(
    @StringRes textResource: Int,
    icon: Drawable? = null,
    crossinline onClicked: DialogOnClick = {}
) {
    neutralButton(context.getString(textResource), icon, onClicked)
}

/**
 * @see AlertDialog.Builder.neutralButton
 */
inline fun AlertDialog.Builder.neutralButton(
    text: CharSequence? = null,
    @DrawableRes iconResource: Int,
    crossinline onClicked: DialogOnClick = {}
) {
    neutralButton(text, ContextCompat.getDrawable(context, iconResource), onClicked)
}

/**
 * @see AlertDialog.Builder.neutralButton
 */
inline fun AlertDialog.Builder.neutralButton(
    @StringRes textResource: Int,
    @DrawableRes iconResource: Int,
    crossinline onClicked: DialogOnClick = {}
) {
    neutralButton(context.getString(textResource), ContextCompat.getDrawable(context, iconResource), onClicked)
}

/**
 * Creates the negative button using the given text, icon and click listener.
 * ## Note
 * This method is [DialogContext]-independent,
 * which means that the `autoDismiss` parameter of [create] and [show] methods won't have any effect on this button.
 * If you would like to make use of that parameter, consider using [DialogContext.negativeButton] instead.
 * @see AlertDialog.Builder.setNegativeButton
 * @see AlertDialog.Builder.setNegativeButtonIcon
 */
inline fun AlertDialog.Builder.negativeButton(
    text: CharSequence? = null,
    icon: Drawable? = null,
    crossinline onClicked: DialogOnClick = {}
) {
    setNegativeButton(text) { dialog, which -> dialog.onClicked(which) }
    icon?.let { setNegativeButtonIcon(it) }
}

/**
 * @see AlertDialog.Builder.negativeButton
 */
inline fun AlertDialog.Builder.negativeButton(
    @StringRes textResource: Int,
    icon: Drawable? = null,
    crossinline onClicked: DialogOnClick = {}
) {
    negativeButton(context.getString(textResource), icon, onClicked)
}

/**
 * @see AlertDialog.Builder.negativeButton
 */
inline fun AlertDialog.Builder.negativeButton(
    text: CharSequence? = null,
    @DrawableRes iconResource: Int,
    crossinline onClicked: DialogOnClick = {}
) {
    negativeButton(text, ContextCompat.getDrawable(context, iconResource), onClicked)
}

/**
 * @see AlertDialog.Builder.negativeButton
 */
inline fun AlertDialog.Builder.negativeButton(
    @StringRes textResource: Int,
    @DrawableRes iconResource: Int,
    crossinline onClicked: DialogOnClick = {}
) {
    negativeButton(context.getString(textResource), ContextCompat.getDrawable(context, iconResource), onClicked)
}

var AlertDialog.Builder.title: CharSequence?
    inline get() = throw UnsupportedOperationException()
    inline set(value) {
        setTitle(value)
    }

var AlertDialog.Builder.titleResource: Int
    inline get() = throw UnsupportedOperationException()
    inline set(value) {
        setTitle(value)
    }

var AlertDialog.Builder.message: CharSequence?
    inline get() = throw UnsupportedOperationException()
    inline set(value) {
        setMessage(value)
    }

var AlertDialog.Builder.messageResource: Int
    inline get() = throw UnsupportedOperationException()
    inline set(value) {
        setMessage(value)
    }

/**
 * @see AlertDialog.Builder.setCustomTitle
 */
var AlertDialog.Builder.titleView: View
    inline get() = throw UnsupportedOperationException()
    inline set(value) {
        setCustomTitle(value)
    }

/**
 * @see AlertDialog.Builder.setCustomTitle
 */
var AlertDialog.Builder.titleViewResource: Int
    inline get() = throw UnsupportedOperationException()
    inline set(value) {
        setCustomTitle(LayoutInflater.from(context).inflate(value, null))
    }

var AlertDialog.Builder.view: View
    inline get() = throw UnsupportedOperationException()
    inline set(value) {
        setView(value)
    }

var AlertDialog.Builder.viewResource: Int
    inline get() = throw UnsupportedOperationException()
    inline set(value) {
        setView(value)
    }

var AlertDialog.Builder.icon: Drawable
    inline get() = throw UnsupportedOperationException()
    inline set(value) {
        setIcon(value)
    }

var AlertDialog.Builder.iconResource: Int
    inline get() = throw UnsupportedOperationException()
    inline set(value) {
        setIcon(value)
    }

var AlertDialog.Builder.isCancelable: Boolean
    inline get() = throw UnsupportedOperationException()
    inline set(value) {
        setCancelable(value)
    }