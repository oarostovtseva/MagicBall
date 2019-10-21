package com.orost.magicball

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.seismic.ShakeDetector
import android.hardware.SensorManager
import com.squareup.seismic.ShakeDetector.SENSITIVITY_LIGHT
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), ShakeDetector.Listener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val sd = ShakeDetector(this)
        sd.setSensitivity(SENSITIVITY_LIGHT)
        sd.start(sensorManager)
    }

    override fun hearShake() {
        //hint.text = "I was shaked..."
    }
}
