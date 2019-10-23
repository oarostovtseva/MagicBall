package com.orost.magicball.utils

import android.animation.Animator
import android.view.View
import android.view.animation.LinearInterpolator

internal fun View.fadeIn(duration: Long) {
    alpha = 0f
    visibility = View.VISIBLE
    animate().setDuration(duration).setInterpolator(LinearInterpolator()).alpha(1f).setListener(null).start()
}

internal fun View.fadeOut(duration: Long, onAnimationEndAction: () -> Unit) {
    clearAnimation()
    alpha = 1f
    animate().setDuration(duration).setInterpolator(LinearInterpolator()).alpha(0f)
        .setListener(object : OnAnimatioEndListener() {
            override fun onAnimationEnd(animation: Animator?) {
                visibility = View.GONE
                alpha = 1f
                onAnimationEndAction.invoke()
            }
        }).start()
}
