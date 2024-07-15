package com.artemissoftware.run.presentation.activerun

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artemissoftware.run.domain.models.RunningTracker
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import timber.log.Timber

class ActiveRunViewModel(
    private val runningTracker: RunningTracker
): ViewModel() {

    var state by mutableStateOf(ActiveRunState())
        private set

    private val _hasLocationPermission = MutableStateFlow(false)

    private val _uiEvent = Channel<ActiveRunUIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        observeLocationPermission()
        observeCurrentLocation()
    }

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

    private fun observeLocationPermission(){
        _hasLocationPermission
            .onEach { hasPermission ->
                if(hasPermission) {
                    runningTracker.startObservingLocation()
                } else {
                    runningTracker.stopObservingLocation()
                }
            }
            .launchIn(viewModelScope)
    }

    private fun observeCurrentLocation(){
        runningTracker
            .currentLocation
            .onEach { location ->
                Timber.d("New location: $location")
            }
            .launchIn(viewModelScope)
    }
}