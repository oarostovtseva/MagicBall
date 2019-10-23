package com.orost.magicball.utils

import android.animation.Animator

/**
 * Abstract class that leaves required implementation only for the onAnimationEnd method
 */
internal abstract class OnAnimatioEndListener : Animator.AnimatorListener {

    override fun onAnimationRepeat(animation: Animator?) {}

    override fun onAnimationCancel(animation: Animator?) {}

    override fun onAnimationStart(animation: Animator?) {}
}

