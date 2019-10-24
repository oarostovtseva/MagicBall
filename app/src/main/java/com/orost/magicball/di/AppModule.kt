package com.orost.magicball.di

import com.google.firebase.analytics.FirebaseAnalytics
import com.orost.magicball.helpscreen.HelpFragment
import com.orost.magicball.shakescreen.ShakeFragment
import com.orost.magicball.shakescreen.ShakeViewModel
import com.orost.magicball.utils.CoroutineContextProvider
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { FirebaseAnalytics.getInstance(androidContext()) }
    single { CoroutineContextProvider() }
    factory { ShakeFragment() }
    factory { HelpFragment() }
    viewModel { ShakeViewModel(get(), get()) }
}