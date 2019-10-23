package com.orost.magicball.di

import com.orost.magicball.shakescreen.ShakeFragment
import com.orost.magicball.shakescreen.ShakeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    factory { ShakeFragment() }
    viewModel { ShakeViewModel(get()) }
}