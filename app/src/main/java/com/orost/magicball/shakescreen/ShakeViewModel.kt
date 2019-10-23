package com.orost.magicball.shakescreen

import android.content.Context
import androidx.lifecycle.ViewModel
import com.orost.magicball.R
import kotlin.random.Random

class ShakeViewModel(applicationContext: Context) : ViewModel() {

    private val answersArray: List<String> = applicationContext.resources.getStringArray(R.array.answers).asList()

    fun getAnswer() = answersArray.getRandomValue()

}

private fun List<String>.getRandomValue() = get(Random.nextInt(0, size))
