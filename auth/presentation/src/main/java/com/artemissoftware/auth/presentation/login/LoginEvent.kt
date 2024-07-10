package com.artemissoftware.auth.presentation.login

sealed interface LoginEvent {
    data object OnTogglePasswordVisibility: LoginEvent
    data object OnLoginClick: LoginEvent
    data object OnRegisterClick: LoginEvent
}