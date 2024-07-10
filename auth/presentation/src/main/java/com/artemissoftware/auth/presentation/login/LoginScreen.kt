@file:OptIn(ExperimentalFoundationApi::class)

package com.artemissoftware.auth.presentation.login

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.artemissoftware.auth.presentation.R
import com.artemissoftware.auth.presentation.composables.Statement
import com.artemissoftware.core.presentation.designsystem.EmailIcon
import com.artemissoftware.core.presentation.designsystem.RuniqueTheme
import com.artemissoftware.core.presentation.designsystem.composables.GradientBackground
import com.artemissoftware.core.presentation.designsystem.composables.buttons.RuniqueButton
import com.artemissoftware.core.presentation.designsystem.composables.textfield.RuniquePasswordTextField
import com.artemissoftware.core.presentation.designsystem.composables.textfield.RuniqueTextField
import com.artemissoftware.core.presentation.ui.ObserveAsEvents
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    onSignUpClick: () -> Unit,
    viewModel: LoginViewModel = koinViewModel(),
) {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current

    ObserveAsEvents(viewModel.uiEvent) { event ->
        when(event) {
            is LoginUIEvent.Error -> {
                keyboardController?.hide()
                Toast.makeText(
                    context,
                    event.error.asString(context),
                    Toast.LENGTH_LONG
                ).show()
            }
            LoginUIEvent.LoginSuccess -> {
                keyboardController?.hide()
                Toast.makeText(
                    context,
                    R.string.youre_logged_in,
                    Toast.LENGTH_LONG
                ).show()

                onLoginSuccess()
            }
        }
    }
    LoginScreenContent(
        state = viewModel.state,
        onEvent = { event ->
            when(event) {
                is LoginEvent.OnRegisterClick -> onSignUpClick()
                else -> Unit
            }
            viewModel.onTriggerEvent(event)
        }
    )
}

@Composable
private fun LoginScreenContent(
    state: LoginState,
    onEvent: (LoginEvent) -> Unit
) {
    GradientBackground {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp)
                .padding(vertical = 32.dp)
                .padding(top = 16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.hi_there),
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = stringResource(id = R.string.runique_welcome_text),
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(48.dp))

            RuniqueTextField(
                state = state.email,
                startIcon = EmailIcon,
                endIcon = null,
                hint = stringResource(id = R.string.example_email),
                title = stringResource(id = R.string.email),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            RuniquePasswordTextField(
                state = state.password,
                isPasswordVisible = state.isPasswordVisible,
                onTogglePasswordVisibility = {
                    onEvent(LoginEvent.OnTogglePasswordVisibility)
                },
                hint = stringResource(id = R.string.password),
                title = stringResource(id = R.string.password),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(32.dp))
            RuniqueButton(
                text = stringResource(id = R.string.login),
                isLoading = state.isLoggingIn,
                enabled = state.canLogin,
                onClick = {
                    onEvent(LoginEvent.OnLoginClick)
                },
            )
            Box(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .weight(1f),
                contentAlignment = Alignment.BottomCenter
            ) {
                Statement(
                    text = R.string.dont_have_an_account,
                    textToClick = R.string.sign_up,
                    onClick = {
                        onEvent(LoginEvent.OnRegisterClick)
                    },
                    textColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Preview
@Composable
private fun LoginScreenContentPreview() {
    RuniqueTheme {
        LoginScreenContent(
            state = LoginState(),
            onEvent = {}
        )
    }
}