package com.orost.magicball.utils

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

internal fun AppCompatActivity.addFragment(
    containerId: Int,
    fragment: Fragment,
    arguments: Bundle? = null,
    animation: ScreenAnimations? = null
) {
    arguments?.let { fragment.arguments = it }
    supportFragmentManager.beginTransaction().apply {
        animation?.let { setCustomAnimations(it.enter, it.exit, it.popEnter, it.popExit) }
        add(containerId, fragment)
        commit()
    }
}

internal fun AppCompatActivity.replaceFragment(
    containerId: Int,
    fragment: Fragment,
    tag: String = "",
    arguments: Bundle? = null,
    animation: ScreenAnimations? = null
) {
    arguments?.let { fragment.arguments = it }
    supportFragmentManager.beginTransaction().apply {
        animation?.let { setCustomAnimations(it.enter, it.exit, it.popEnter, it.popExit) }
        replace(containerId, fragment, tag)
        addToBackStack(null)
        commit()
    }
}
