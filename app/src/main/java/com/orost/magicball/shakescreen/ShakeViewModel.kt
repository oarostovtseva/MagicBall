package com.orost.magicball.shakescreen

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orost.magicball.R
import com.orost.magicball.utils.CoroutineContextProvider
import kotlinx.coroutines.*
import kotlinx.coroutines.NonCancellable.isCancelled
import timber.log.Timber
import kotlin.random.Random

internal const val SHOW_ANSWER_DELAY = 2000L

class ShakeViewModel(
    applicationContext: Context,
    private val coroutineContextProvider: CoroutineContextProvider
) : ViewModel() {

    private val answersArray: List<String> = applicationContext.resources.getStringArray(R.array.answers).asList()

    private var isInProgress = false // We get action twice from the shakeDetector sometimes

    val answer = MutableLiveData<String>()

    fun getAnswer() {
        if (isInProgress) return else isInProgress = true

        viewModelScope.launch(coroutineContextProvider.io) {
            answer.postValue(answersArray.getRandomValue())
            delay(SHOW_ANSWER_DELAY)
            answer.postValue("")
            isInProgress = false
        }
    }

}

private fun List<String>.getRandomValue() = get(Random.nextInt(0, size))
