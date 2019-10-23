package com.orost.magicball.utils

import androidx.annotation.AnimRes
import androidx.annotation.AnimatorRes

internal const val noAnimationRes = 0

internal sealed class ScreenAnimations(
        @AnimatorRes @AnimRes val enter: Int,
        @AnimatorRes @AnimRes val exit: Int,
        @AnimatorRes @AnimRes val popEnter: Int,
        @AnimatorRes @AnimRes val popExit: Int
)

internal object SlideAnimation : ScreenAnimations(
        android.R.anim.slide_out_right,
        noAnimationRes,
        android.R.anim.slide_in_left,
        noAnimationRes
)
