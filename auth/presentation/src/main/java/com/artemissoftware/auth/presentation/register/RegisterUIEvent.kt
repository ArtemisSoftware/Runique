package com.artemissoftware.auth.presentation.register

import com.artemissoftware.core.presentation.ui.UiText

sealed interface RegisterUIEvent {
    data object RegistrationSuccess: RegisterUIEvent
    data class Error(val error: UiText): RegisterUIEvent
}