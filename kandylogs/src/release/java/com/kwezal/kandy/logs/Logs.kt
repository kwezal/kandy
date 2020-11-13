@file:JvmName("Logs")

package com.kwezal.kandy.logs

inline fun Any.logV(tr: Throwable? = null, msg: () -> String) {}
inline fun Any.logD(tr: Throwable? = null, msg: () -> String) {}
inline fun Any.logI(tr: Throwable? = null, msg: () -> String) {}
inline fun Any.logW(tr: Throwable? = null, msg: () -> String) {}
inline fun Any.logE(tr: Throwable? = null, msg: () -> String) {}
inline fun Any.logWtf(tr: Throwable? = null, msg: () -> String) {}

inline val Any.tag get() = ""