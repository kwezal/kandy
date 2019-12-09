package com.kwezal.kandy.listviews.interfaces

import android.content.Context
import android.view.View

/**
 * A function that creates a [View] for list item using given [Context].
 */
typealias IKandyItemViewCreator = (Context).() -> View
