package com.kwezal.kandy.logs

import android.util.Log

inline fun Any.logV(msg: String, tr: Throwable? = null) {
    if (tr == null) {
        Log.v(tag, msg)
    } else {
        Log.v(tag, msg, tr)
    }
}

inline fun Any.logD(msg: String, tr: Throwable? = null) {
    if (tr == null) {
        Log.d(tag, msg)
    } else {
        Log.d(tag, msg, tr)
    }
}

inline fun Any.logI(msg: String, tr: Throwable? = null) {
    if (tr == null) {
        Log.i(tag, msg)
    } else {
        Log.i(tag, msg, tr)
    }
}

inline fun Any.logW(msg: String, tr: Throwable? = null) {
    if (tr == null) {
        Log.w(tag, msg)
    } else {
        Log.w(tag, msg, tr)
    }
}

inline fun Any.logE(msg: String, tr: Throwable? = null) {
    if (tr == null) {
        Log.e(tag, msg)
    } else {
        Log.e(tag, msg, tr)
    }
}

inline fun Any.logWtf(msg: String, tr: Throwable? = null) {
    if (tr == null) {
        Log.wtf(tag, msg)
    } else {
        Log.wtf(tag, msg, tr)
    }
}

val Any.tag: String
    get() {
        val simpleName = javaClass.simpleName
        return if (simpleName.isNotEmpty())
            simpleName
        else {
            val name = javaClass.name.substringAfterLast('.')
            if (name.isNotEmpty())
                name
            else
                "Unknown context"
        }
    }