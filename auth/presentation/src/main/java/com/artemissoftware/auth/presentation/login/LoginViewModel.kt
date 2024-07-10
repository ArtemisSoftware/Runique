package com.artemissoftware.auth.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.artemissoftware.auth.domain.repository.AuthRepository
import com.artemissoftware.auth.presentation.register.RegisterUIEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class LoginViewModel(
    private val authRepository: AuthRepository
): ViewModel() {

    var state by mutableStateOf(LoginState())
        private set

    private val _uiEvent = Channel<LoginUIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onTriggerEvent(event: LoginEvent) {

    }
}