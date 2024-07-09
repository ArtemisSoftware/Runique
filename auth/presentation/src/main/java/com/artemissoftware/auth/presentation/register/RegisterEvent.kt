package com.artemissoftware.auth.presentation.register

sealed interface RegisterEvent {
    data object OnTogglePasswordVisibilityClick: RegisterEvent
    data object OnLoginClick: RegisterEvent
    data object OnRegisterClick: RegisterEvent
}