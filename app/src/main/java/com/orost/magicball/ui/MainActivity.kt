package com.orost.magicball.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.orost.magicball.R
import com.orost.magicball.shakescreen.ShakeFragment
import com.orost.magicball.utils.addFragment
import org.koin.android.ext.android.get


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            addFragment(R.id.fragment_container, get<ShakeFragment>())
        }
    }
}
