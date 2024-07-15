package com.artemissoftware.run.presentation.di

import com.artemissoftware.run.domain.models.RunningTracker
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val runPresentationModule = module {
    singleOf(::RunningTracker)
}