package com.orost.magicball.shakescreen

import android.content.Context
import android.hardware.SensorManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.google.firebase.analytics.FirebaseAnalytics
import com.orost.magicball.R
import com.orost.magicball.helpscreen.HelpFragment
import com.orost.magicball.ui.BaseFragment
import com.orost.magicball.utils.FadeAnimation
import com.orost.magicball.utils.fadeIn
import com.orost.magicball.utils.fadeOut
import com.orost.magicball.utils.replaceWithBackStack
import com.orost.magicball.utils.shake
import com.squareup.seismic.ShakeDetector
import kotlinx.android.synthetic.main.fragment_shake.*
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import timber.log.Timber

internal const val ANIMATION_FADE_DURATION = 500L

class ShakeFragment : BaseFragment(), ShakeDetector.Listener {

    private val sensorManager: SensorManager by lazy {
        requireContext().getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    private val shakeDetector: ShakeDetector = ShakeDetector(this).apply { setSensitivity(ShakeDetector.SENSITIVITY_LIGHT) }

    private val firebaseAnalytics: FirebaseAnalytics by inject()
    private val shakeViewModel: ShakeViewModel by inject()

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        startShakeDetector()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_shake, container, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        stopShakeDetector()
    }

    override fun initUI(savedInstanceState: Bundle?) {
        btn_help.setOnClickListener {
            fragmentManager?.replaceWithBackStack(
                    R.id.fragment_container,
                    get<HelpFragment>(),
                    FadeAnimation
            )
        }
    }

    override fun subscribeToLiveData() {
        shakeViewModel.answer.observe(this, Observer {
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

    private fun startShakeDetector() {
        shakeDetector.start(sensorManager)
        Timber.d("Shake detector was initialized")
    }

    private fun stopShakeDetector() {
        shakeDetector.stop()
        Timber.d("Shake detector was stopped")
    }

    private fun sendAnalytics(answer: String) {
        if (answer.isEmpty()) return
        Timber.d("Got an answer: $answer")
        val bundle = Bundle().apply {
            putString("answer", answer)
        }
        firebaseAnalytics.logEvent("shaking", bundle)
    }
}
