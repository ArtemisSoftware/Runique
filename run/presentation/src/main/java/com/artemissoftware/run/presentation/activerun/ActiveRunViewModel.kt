package com.artemissoftware.run.presentation.activerun

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

class ActiveRunViewModel: ViewModel() {

    var state by mutableStateOf(ActiveRunState())
        private set

    private val _hasLocationPermission = MutableStateFlow(false)

    private val _uiEvent = Channel<ActiveRunUIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onTriggerEvent(event: ActiveRunEvent) {
        when(event) {
            ActiveRunEvent.OnFinishRunClick -> {

            }
            ActiveRunEvent.OnResumeRunClick -> {

            }
            ActiveRunEvent.OnToggleRunClick -> {

            }
            is ActiveRunEvent.SubmitLocationPermissionInfo -> {
                updateLocationPermission(
                    hasLocationPermission = event.showLocationRationale,
                    acceptedLocationPermission = event.acceptedLocationPermission
                )
            }
            is ActiveRunEvent.SubmitNotificationPermissionInfo -> {
                updateNotificationPermission(event.showNotificationPermissionRationale)
            }
            is ActiveRunEvent.DismissRationaleDialog -> {
                dismissRationaleDialog()
            }
            else -> Unit
        }
    }

    private fun updateLocationPermission(hasLocationPermission: Boolean, acceptedLocationPermission: Boolean){
        _hasLocationPermission.value = acceptedLocationPermission
        state = state.copy(
            showLocationRationale = hasLocationPermission
        )
    }

    private fun updateNotificationPermission(hasPermission: Boolean){
        state = state.copy(
            showNotificationRationale = hasPermission
        )
    }

    private fun dismissRationaleDialog(){
        state = state.copy(
            showNotificationRationale = false,
            showLocationRationale = false
        )
    }
}