package com.kwezal.kandy.dialogs

import android.content.Context
import android.content.DialogInterface
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat

/**
 * The code to launch after the dialog button click.
 * @see DialogInterface.OnClickListener
 */
typealias DialogOnClick = DialogInterface.(which: Int) -> Unit

/**
 * The most basic data about the dialog button,
 * which can be used to manage buttons in dialog boxes created using [DialogContext] methods.
 * @param text the text to display
 * @param icon the icon to display
 * @param onClicked the listener to use
 */
class DialogButton(
    val text: CharSequence,
    val icon: Drawable? = null,
    val onClick: DialogOnClick = {}
) {

    constructor(
        ctx: Context,
        @StringRes textResource: Int,
        icon: Drawable? = null,
        onClick: DialogOnClick = {}
    ) : this(ctx.getString(textResource), icon, onClick)

    constructor(
        ctx: Context,
        text: CharSequence,
        @DrawableRes iconResource: Int,
        onClick: DialogOnClick = {}
    ) : this(text, ContextCompat.getDrawable(ctx, iconResource), onClick)

    constructor(
        ctx: Context,
        @StringRes textResource: Int,
        @DrawableRes iconResource: Int,
        onClick: DialogOnClick = {}
    ) : this(ctx.getString(textResource), ContextCompat.getDrawable(ctx, iconResource), onClick)

    @Deprecated("This field will be removed in the future", ReplaceWith("onClick"))
    inline val onClicked get() = onClick
}