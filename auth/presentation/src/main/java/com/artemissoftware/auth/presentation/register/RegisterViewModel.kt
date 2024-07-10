@file:OptIn(ExperimentalFoundationApi::class)

package com.artemissoftware.auth.presentation.register

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.text2.input.textAsFlow
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artemissoftware.auth.domain.UserDataValidator
import com.artemissoftware.auth.domain.repository.AuthRepository
import com.artemissoftware.auth.presentation.R
import com.artemissoftware.core.domain.util.DataError
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import com.artemissoftware.core.domain.util.Result
import com.artemissoftware.core.presentation.ui.UiText
import com.artemissoftware.core.presentation.ui.asUiText

class RegisterViewModel(
    private val userDataValidator: UserDataValidator,
    private val authRepository: AuthRepository
): ViewModel() {

    var state by mutableStateOf(RegisterState())
        private set

    private val _uiEvent = Channel<RegisterUIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        updateEmail()
        updatePassword()
    }

    fun onTriggerEvent(event: RegisterEvent) {
        when(event){
            RegisterEvent.OnLoginClick -> Unit
            RegisterEvent.OnRegisterClick -> register()
            RegisterEvent.OnTogglePasswordVisibilityClick -> updatePasswordVisibility()
        }
    }

    private fun updateEmail(){
        state.email.textAsFlow()
            .onEach { email ->
                val isValidEmail = userDataValidator.isValidEmail(email.toString())

                state = state.copy(
                    isEmailValid = userDataValidator.isValidEmail(email.toString()),
                    canRegister = isValidEmail && state.passwordValidationState.isValidPassword
                            && !state.isRegistering
                )
            }
            .launchIn(viewModelScope)
    }

    private fun updatePassword(){
        state.password.textAsFlow()
            .onEach { password ->
                val passwordValidationState = userDataValidator.validatePassword(password.toString())

                state = state.copy(
                    passwordValidationState = userDataValidator.validatePassword(password.toString()),
                    canRegister = state.isEmailValid && passwordValidationState.isValidPassword
                            && !state.isRegistering
                )
            }
            .launchIn(viewModelScope)
    }

    private fun register() {
        viewModelScope.launch {
            state = state.copy(isRegistering = true)

            val result = authRepository.register(
                email = state.email.text.toString().trim(),
                password = state.password.text.toString()
            )
            state = state.copy(isRegistering = false)

            when(result) {
                is Result.Error -> {
                    if(result.error == DataError.Network.CONFLICT) {
                        _uiEvent.send(RegisterUIEvent.Error(
                            UiText.StringResource(R.string.error_email_exists)
                        ))
                    } else {
                        _uiEvent.send(RegisterUIEvent.Error(result.error.asUiText()))
                    }
                }
                is Result.Success -> {
                    _uiEvent.send(RegisterUIEvent.RegistrationSuccess)
                }
            }
        }
    }

    private fun updatePasswordVisibility(){
        state = state.copy(
            isPasswordVisible = !state.isPasswordVisible
        )
    }
}