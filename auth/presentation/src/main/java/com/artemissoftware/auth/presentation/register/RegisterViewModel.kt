package com.artemissoftware.auth.presentation.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class RegisterViewModel: ViewModel() {

    var state by mutableStateOf(RegisterState())
        private set

    fun onTriggerEvent(event: RegisterEvent) {

    }
}