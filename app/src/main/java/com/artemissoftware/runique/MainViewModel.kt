package com.artemissoftware.runique

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artemissoftware.core.domain.repository.SessionRepository
import kotlinx.coroutines.launch

class MainViewModel(
    private val sessionRepository: SessionRepository
): ViewModel() {

    var state by mutableStateOf(MainState())
        private set

    init {
        getUserLoggedState()
    }

    private fun getUserLoggedState(){
        viewModelScope.launch {
            state = state.copy(isCheckingAuth = true)
            state = state.copy(
                isLoggedIn = sessionRepository.get() != null
            )
            state = state.copy(isCheckingAuth = false)
        }
    }
}