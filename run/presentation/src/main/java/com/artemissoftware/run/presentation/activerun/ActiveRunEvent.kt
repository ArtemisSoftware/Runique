package com.artemissoftware.run.presentation.activerun

sealed  interface ActiveRunEvent {
    data object OnToggleRunClick: ActiveRunEvent
    data object OnFinishRunClick: ActiveRunEvent
    data object OnResumeRunClick: ActiveRunEvent
    data object OnBackClick: ActiveRunEvent

    data class SubmitLocationPermissionInfo(
        val acceptedLocationPermission: Boolean,
        val showLocationRationale: Boolean
    ): ActiveRunEvent

    data class SubmitNotificationPermissionInfo(
        val acceptedNotificationPermission: Boolean,
        val showNotificationPermissionRationale: Boolean
    ): ActiveRunEvent

    data object DismissRationaleDialog: ActiveRunEvent
}