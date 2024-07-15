package com.artemissoftware.run.di

import com.artemissoftware.run.domain.observers.LocationObserver
import com.artemissoftware.run.location.AndroidLocationObserver
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val locationModule = module {
    singleOf(::AndroidLocationObserver).bind<LocationObserver>()
}