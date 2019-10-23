package com.orost.magicball.utils

import android.util.Log
import timber.log.Timber

internal class ReleaseTree : Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (priority == Log.VERBOSE || priority == Log.DEBUG) {
            return
        }
        Timber.log(priority, tag, message, t)
    }
}