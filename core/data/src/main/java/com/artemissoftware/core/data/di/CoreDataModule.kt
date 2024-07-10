package com.artemissoftware.core.data.di

import com.artemissoftware.core.data.networking.HttpClientFactory
import com.artemissoftware.core.data.repository.EncryptedSessionRepository
import com.artemissoftware.core.domain.repository.SessionRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.dsl.single

val coreDataModule = module {
    single {
        HttpClientFactory().build()
    }

    singleOf(::EncryptedSessionRepository).bind<SessionRepository>()
}