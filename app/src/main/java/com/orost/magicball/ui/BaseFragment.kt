package com.orost.magicball.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

/**
 * Contains common methods which init views inside fragment and initiate data loading
 */
abstract class BaseFragment : Fragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI(savedInstanceState)
        subscribeToLiveData()
    }

    /**
     * Hook method for initialize UI elements. Called inside [onViewCreated]
     */
    open fun initUI(savedInstanceState: Bundle?) {}

    /**
     * Hook method to make a subscription to live data. Called inside [onViewCreated]
     */
    open fun subscribeToLiveData() {}
}