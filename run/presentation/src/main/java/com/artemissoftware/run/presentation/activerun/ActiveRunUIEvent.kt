package com.artemissoftware.run.presentation.activerun

import com.artemissoftware.core.presentation.ui.UiText

sealed interface ActiveRunUIEvent {
    data class Error(val error: UiText): ActiveRunUIEvent
    data object RunSaved: ActiveRunUIEvent
}