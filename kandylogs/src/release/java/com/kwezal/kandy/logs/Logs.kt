@file:JvmName("Logs")

package com.kwezal.kandy.logs

inline fun Any.logV(
    customTag: () -> String? = { null },
    tr: () -> Throwable? = { null },
    msg: () -> String
) {
}

inline fun Any.logD(
    customTag: () -> String? = { null },
    tr: () -> Throwable? = { null },
    msg: () -> String
) {
}

inline fun Any.logI(
    customTag: () -> String? = { null },
    tr: () -> Throwable? = { null },
    msg: () -> String
) {
}

inline fun Any.logW(
    customTag: () -> String? = { null },
    tr: () -> Throwable? = { null },
    msg: () -> String
) {
}

inline fun Any.logE(
    customTag: () -> String? = { null },
    tr: () -> Throwable? = { null },
    msg: () -> String
) {
}

inline fun Any.logWtf(
    customTag: () -> String? = { null },
    tr: () -> Throwable? = { null },
    msg: () -> String
) {
}

inline val Any.tag get() = ""