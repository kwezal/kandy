package com.kwezal.kandy.listviews

import com.kwezal.kandy.listviews.base.AbstractKandyItemView
import com.kwezal.kandy.listviews.interfaces.IKandyItemViewCreator

/**
 * The simplest item view which can be created by passing [createView] function as an argument.
 * It provides an alternative to creating a subclass of [AbstractKandyItemView].
 * ## Comparison
 * ### KandyItemView way
 * ```
 * val view = KandyItemView(42) {View(this)}
 * ```
 * ### AbstractKandyItemView way
 * ```
 * class MyKandyItemView : AbstractKandyItemView(42) {
 *     override val createView: IKandyItemViewCreator = {View(this)}
 * }
 * val view = MyKandyItemView()
 * ```
 * ## Performance note
 * For huge amount of list items it's a bad idea to make separate [createView] function for each element.
 * In that case consider sharing the instance or create a subclass of [AbstractKandyItemView].
 * ### Example
 * ```
 * val createView: IKandyItemViewCreator = {View(this)}
 * val views = ArrayList<KandyItemView>().apply {
 *     for(i in 0 until 100_000)
 *         add(KandyItemView(42, createView))
 * }
 * ```
 * ## Final note
 * This class is closed by purpose.
 * The main idea here is to avoid class creation.
 * If some custom behavior is needed, extend [AbstractKandyItemView] instead.
 */
class KandyItemView(type: Int = 0, override val createView: IKandyItemViewCreator) :
    AbstractKandyItemView(type)