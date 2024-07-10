package com.artemissoftware.auth.presentation.login

import com.artemissoftware.core.presentation.ui.UiText


sealed interface LoginUIEvent {
    data class Error(val error: UiText): LoginUIEvent
    data object LoginSuccess: LoginUIEvent
}