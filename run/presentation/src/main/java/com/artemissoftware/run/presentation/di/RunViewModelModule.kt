package com.artemissoftware.run.presentation.di

import com.artemissoftware.run.presentation.activerun.ActiveRunViewModel
import com.artemissoftware.run.presentation.overview.OverviewViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val runViewModelModule = module {
    viewModelOf(::OverviewViewModel)
    viewModelOf(::ActiveRunViewModel)
}