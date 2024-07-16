package com.artemissoftware.run.presentation.activerun

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artemissoftware.run.domain.models.RunningTracker
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import timber.log.Timber

class ActiveRunViewModel(
    private val runningTracker: RunningTracker
): ViewModel() {

    var state by mutableStateOf(ActiveRunState())
        private set

    private val _uiEvent = Channel<ActiveRunUIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val shouldTrack = snapshotFlow { state.shouldTrack }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = state.shouldTrack
        )

    private val hasLocationPermission = MutableStateFlow(false)

    private val isTracking = combine(
        shouldTrack,
        hasLocationPermission
    ) { shouldTrack, hasPermission ->
        shouldTrack && hasPermission
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = false
    )

    init {
        observeLocationPermission()
        observeIsTracking()
        observeCurrentLocation()
        observeRunData()
        observeElapsedTime()
    }

    fun onTriggerEvent(event: ActiveRunEvent) {
        when(event) {
            ActiveRunEvent.OnFinishRunClick -> {

            }
            ActiveRunEvent.OnResumeRunClick -> {
                state = state.copy(shouldTrack = true)
            }
            ActiveRunEvent.OnBackClick -> {
                state = state.copy(shouldTrack = false)
            }
            ActiveRunEvent.OnToggleRunClick -> {
                state = state.copy(
                    hasStartedRunning = true,
                    shouldTrack = !state.shouldTrack
                )
            }
            is ActiveRunEvent.SubmitLocationPermissionInfo -> {
                updateLocationPermission(
                    isLocationPermissionGiven = event.showLocationRationale,
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

    private fun updateLocationPermission(isLocationPermissionGiven: Boolean, acceptedLocationPermission: Boolean){
        hasLocationPermission.value = acceptedLocationPermission
        state = state.copy(
            showLocationRationale = isLocationPermissionGiven
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
        hasLocationPermission
            .onEach { hasPermission ->
                Timber.d("has Permission: $hasPermission")
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
            .onEach { currentLocation ->
                Timber.d("current Location: $currentLocation")
                state = state.copy(currentLocation = currentLocation?.location)
            }
            .launchIn(viewModelScope)
    }

    private fun observeIsTracking(){
        isTracking
            .onEach { isTracking ->
                Timber.d("is Tracking: $isTracking")
                runningTracker.setIsTracking(isTracking)
            }
            .launchIn(viewModelScope)
    }

    private fun observeRunData(){
        runningTracker
            .runData
            .onEach {
                Timber.d("run Data: $it")
                state = state.copy(runData = it)
            }
            .launchIn(viewModelScope)
    }

    private fun observeElapsedTime(){
        runningTracker
            .elapsedTime
            .onEach {
                Timber.d("elapsed Time: $it")
                state = state.copy(elapsedTime = it)
            }
            .launchIn(viewModelScope)
    }
}