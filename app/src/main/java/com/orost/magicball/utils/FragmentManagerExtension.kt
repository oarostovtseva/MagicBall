package com.orost.magicball.utils

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

/**
 *
 * Provides some useful methods as extensions to [FragmentManager]
 *
 */
internal fun FragmentManager.replaceWithBackStack(
    @IdRes containerId: Int,
    fragment: Fragment,
    animation: ScreenAnimations? = null
) {
    beginTransaction().apply {
        animation?.let { setCustomAnimations(it.enter, it.exit, it.popEnter, it.popExit) }
        replace(containerId, fragment)
        addToBackStack(null)
        commit()
    }
}
