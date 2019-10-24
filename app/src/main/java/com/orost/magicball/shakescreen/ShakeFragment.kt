package com.orost.magicball.shakescreen

import android.content.Context
import android.hardware.SensorManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.google.firebase.analytics.FirebaseAnalytics
import com.orost.magicball.MBApplication
import com.orost.magicball.R
import com.orost.magicball.ui.BaseFragment
import com.orost.magicball.utils.fadeIn
import com.orost.magicball.utils.fadeOut
import com.orost.magicball.utils.shake
import com.squareup.seismic.ShakeDetector
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.android.ext.android.inject
import timber.log.Timber

internal const val ANIMATION_FADE_DURATION = 500L

class ShakeFragment : BaseFragment(), ShakeDetector.Listener {

    private val firebaseAnalytics: FirebaseAnalytics by lazy { FirebaseAnalytics.getInstance(requireActivity()) }
    private val shakeViewModel: ShakeViewModel by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        initShakeDetector()
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun subscribeToLiveData() {
        shakeViewModel.answer.observe(this, Observer {
            Timber.d("Got an answer: $it")
            sendAnalytics(it)
            answer_bg.shake()
            answer_text.fadeOut(ANIMATION_FADE_DURATION) {
                answer_text.text = it
                answer_text.fadeIn(ANIMATION_FADE_DURATION)
            }
        })
    }

    override fun hearShake() {
        shakeViewModel.getAnswer()
    }

    private fun initShakeDetector() {
        val sensorManager = requireContext().getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val sd = ShakeDetector(this)
        sd.setSensitivity(ShakeDetector.SENSITIVITY_LIGHT)
        sd.start(sensorManager)
        Timber.d("Shake detector was initialized")
    }

    private fun sendAnalytics(answer: String) {
        val bundle = Bundle().apply {
            putString("action_shaking", "Got an answer: $answer")
        }
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle)
    }
}
