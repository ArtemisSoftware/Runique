package com.artemissoftware.run.presentation.activerun

sealed  interface ActiveRunEvent {
    data object OnToggleRunClick: ActiveRunEvent
    data object OnFinishRunClick: ActiveRunEvent
    data object OnResumeRunClick: ActiveRunEvent
    data object OnBackClick: ActiveRunEvent
}