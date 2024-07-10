package com.artemissoftware.core.di

import com.artemissoftware.core.data.HttpClientFactory
import org.koin.dsl.module

val coreDataModule = module {
    single {
        HttpClientFactory().build()
    }
}