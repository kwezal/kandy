package com.kwezal.kandy.listviews.interfaces

import android.view.View
import com.kwezal.kandy.listviews.AbstractAnyKandyViewHolder

/**
 * A function that creates a view holder using given [View].
 */
typealias IKandyViewHolderCreator = (itemView: View) -> AbstractAnyKandyViewHolder