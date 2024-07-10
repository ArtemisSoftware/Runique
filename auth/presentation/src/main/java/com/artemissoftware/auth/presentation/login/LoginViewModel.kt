@file:Suppress("OPT_IN_USAGE_FUTURE_ERROR")
@file:OptIn(ExperimentalFoundationApi::class)

package com.artemissoftware.auth.presentation.login

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.text2.input.textAsFlow
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artemissoftware.auth.domain.UserDataValidator
import com.artemissoftware.auth.domain.repository.AuthRepository
import com.artemissoftware.auth.domain.usecase.LoginUseCase
import com.artemissoftware.auth.presentation.R
import com.artemissoftware.core.domain.util.DataError
import com.artemissoftware.core.domain.util.Result
import com.artemissoftware.core.presentation.ui.UiText
import com.artemissoftware.core.presentation.ui.asUiText
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val userDataValidator: UserDataValidator,
    private val loginUseCase: LoginUseCase
): ViewModel() {

    var state by mutableStateOf(LoginState())
        private set

    private val _uiEvent = Channel<LoginUIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        updateEmailPassword()
    }

    fun onTriggerEvent(event: LoginEvent) {
        when(event){
            LoginEvent.OnLoginClick -> login()
            LoginEvent.OnRegisterClick -> Unit
            LoginEvent.OnTogglePasswordVisibility -> updatePasswordVisibility()
        }
    }

    private fun updateEmailPassword(){
        combine(state.email.textAsFlow(), state.password.textAsFlow()) { email, password ->
            state = state.copy(
                canLogin = userDataValidator.isValidEmail(
                    email = email.toString().trim()
                ) && password.isNotEmpty()
            )
        }.launchIn(viewModelScope)
    }

    private fun updatePasswordVisibility(){
        state = state.copy(
            isPasswordVisible = !state.isPasswordVisible
        )
    }

    private fun login() {
        viewModelScope.launch {
            state = state.copy(isLoggingIn = true)
            val result = loginUseCase(
                email = state.email.text.toString().trim(),
                password = state.password.text.toString()
            )
            state = state.copy(isLoggingIn = false)

            when(result) {
                is Result.Error -> {
                    if(result.error == DataError.Network.UNAUTHORIZED) {
                        _uiEvent.send(LoginUIEvent.Error(
                            UiText.StringResource(R.string.error_email_password_incorrect)
                        ))
                    } else {
                        _uiEvent.send(LoginUIEvent.Error(result.error.asUiText()))
                    }
                }
                is Result.Success -> {
                    _uiEvent.send(LoginUIEvent.LoginSuccess)
                }
            }
        }
    }
}