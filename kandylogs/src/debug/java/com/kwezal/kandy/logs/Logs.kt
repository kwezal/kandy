@file:JvmName("Logs")

package com.kwezal.kandy.logs

import android.util.Log

inline fun Any.logV(
    customTag: () -> String? = { null },
    tr: () -> Throwable? = { null },
    msg: () -> String
) {
    val throwable = tr()
    if (throwable == null) {
        Log.v(customTag() ?: tag, msg())
    } else {
        Log.v(customTag() ?: tag, msg(), throwable)
    }
}

inline fun Any.logD(
    customTag: () -> String? = { null },
    tr: () -> Throwable? = { null },
    msg: () -> String
) {
    val throwable = tr()
    if (throwable == null) {
        Log.d(customTag() ?: tag, msg())
    } else {
        Log.d(customTag() ?: tag, msg(), throwable)
    }
}

inline fun Any.logI(
    customTag: () -> String? = { null },
    tr: () -> Throwable? = { null },
    msg: () -> String
) {
    val throwable = tr()
    if (throwable == null) {
        Log.i(customTag() ?: tag, msg())
    } else {
        Log.i(customTag() ?: tag, msg(), throwable)
    }
}

inline fun Any.logW(
    customTag: () -> String? = { null },
    tr: () -> Throwable? = { null },
    msg: () -> String
) {
    val throwable = tr()
    if (throwable == null) {
        Log.w(customTag() ?: tag, msg())
    } else {
        Log.w(customTag() ?: tag, msg(), throwable)
    }
}

inline fun Any.logE(
    customTag: () -> String? = { null },
    tr: () -> Throwable? = { null },
    msg: () -> String
) {
    val throwable = tr()
    if (throwable == null) {
        Log.e(customTag() ?: tag, msg())
    } else {
        Log.e(customTag() ?: tag, msg(), throwable)
    }
}

inline fun Any.logWtf(
    customTag: () -> String? = { null },
    tr: () -> Throwable? = { null },
    msg: () -> String
) {
    val throwable = tr()
    if (throwable == null) {
        Log.wtf(customTag() ?: tag, msg())
    } else {
        Log.wtf(customTag() ?: tag, msg(), throwable)
    }
}

private const val TAG_MAX_LENGTH = 23

val Any.tag: String
    get() {
        val name = javaClass.name.substringAfterLast('.')
        return if (name.isNotEmpty()) {
            if (name.length > TAG_MAX_LENGTH) {
                "…${name.substring(name.length - TAG_MAX_LENGTH + 1)}"
            } else {
                name
            }.replace("$", ".")
        } else {
            "Unknown context"
        }
    }