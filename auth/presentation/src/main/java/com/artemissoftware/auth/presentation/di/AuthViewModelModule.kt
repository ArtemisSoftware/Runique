package com.artemissoftware.auth.presentation.di

import com.artemissoftware.auth.presentation.login.LoginViewModel
import com.artemissoftware.auth.presentation.register.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val authViewModelModule = module {
    viewModelOf(::RegisterViewModel)
    viewModelOf(::LoginViewModel)
}