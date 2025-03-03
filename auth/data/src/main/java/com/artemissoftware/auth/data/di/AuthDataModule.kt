package com.artemissoftware.auth.data.di

import com.artemissoftware.auth.data.EmailPatternValidator
import com.artemissoftware.auth.data.repository.AuthRepositoryImpl
import com.artemissoftware.auth.domain.PatternValidator
import com.artemissoftware.auth.domain.UserDataValidator
import com.artemissoftware.auth.domain.repository.AuthRepository
import com.artemissoftware.auth.domain.usecase.LoginUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val authDataModule = module {
    single<PatternValidator> {
        EmailPatternValidator
    }
    singleOf(::UserDataValidator)
    singleOf(::LoginUseCase)
    singleOf(::AuthRepositoryImpl).bind<AuthRepository>()
}