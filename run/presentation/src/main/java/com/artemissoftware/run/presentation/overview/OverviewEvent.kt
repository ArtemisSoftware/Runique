package com.artemissoftware.run.presentation.overview

sealed interface OverviewEvent {
    data object OnStartClick: OverviewEvent
    data object OnLogoutClick: OverviewEvent
    data object OnAnalyticsClick: OverviewEvent
}