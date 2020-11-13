package com.kwezal.kandy

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.kwezal.kandy.logs.*
import splitties.views.dsl.core.*
import splitties.views.gravityCenter

class LogsExampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(ui)

        updateLogsStatus(false)
        logI { turnOnLogs() }

        logV { "Blah blah blah..." }
        logD({ "Boom!" }) { "The bug bomb was detonated." }
        logI(customTag = { "Did you know?" }) { "The Quetzal is the national bird of Guatemala. These vibrantly colored animals live in the mountainous, tropical forests of Guatemala where they eat fruit, insects, lizards, and other small creatures." }
        logWtf { "WTF stands for What a Terrible Failure. What did you think?" }

        companionLog()

        StaticClass().staticLog()
        StaticClass.staticCompanionLog()

        InnerClass().innerLog()

        PackageClass().packageLog()
        PackageClass.packageCompanionLog()

        logW { "Attention please! I'm about to show an error message!" }
        logE(tr = { IllegalStateException() }) { "NoErrorException" }
        logE({ "Favorite exception" }, { NullPointerException() }) { "" }
    }

    private inline fun updateLogsStatus(on: Boolean) {
        ui.statusTextView.text = "Logs: ${if (on) "on" else "off"}"
    }

    private fun turnOnLogs(): String {
        updateLogsStatus(true)
        return "Logs have been turned on"
    }

    companion object {
        fun companionLog() {
            logI { "LogsExampleActivity::log()" }
        }
    }

    class StaticClass {
        fun staticLog() {
            logI { "StaticClass.log()" }
        }

        companion object {
            fun staticCompanionLog() {
                logI { "StaticClass::log()" }
            }
        }
    }

    inner class InnerClass {
        fun innerLog() {
            logI { "InnerClass.log()" }
        }
    }

    private val ui by lazy {
        object : Ui {
            override val ctx: Context
                get() = this@LogsExampleActivity

            override val root: View
                get() = frameLayout {
                    add(statusTextView, lParams { gravity = gravityCenter })
                }

            val statusTextView by lazy { textView() }
        }
    }
}

class PackageClass() {
    fun packageLog() {
        logI { "PackageClass.log()" }
    }

    companion object {
        fun packageCompanionLog() {
            logI { "PackageClass::log()" }
        }
    }
}