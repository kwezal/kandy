package com.kwezal.kandy.logs

inline fun Any.logV(msg: String, tr: Throwable? = null) {}
inline fun Any.logD(msg: String, tr: Throwable? = null) {}
inline fun Any.logI(msg: String, tr: Throwable? = null) {}
inline fun Any.logW(msg: String, tr: Throwable? = null) {}
inline fun Any.logE(msg: String, tr: Throwable? = null) {}
inline fun Any.logWtf(msg: String, tr: Throwable? = null) {}
inline val Any.tag get() = ""