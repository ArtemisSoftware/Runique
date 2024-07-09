package com.artemissoftware.auth.presentation.intro

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.artemissoftware.auth.presentation.R
import com.artemissoftware.auth.presentation.intro.composables.RuniqueLogoVertical
import com.artemissoftware.core.presentation.designsystem.RuniqueTheme
import com.artemissoftware.core.presentation.designsystem.composables.GradientBackground
import com.artemissoftware.core.presentation.designsystem.composables.buttons.RuniqueButton
import com.artemissoftware.core.presentation.designsystem.composables.buttons.RuniqueOutlinedButton

@Composable
fun IntroScreen(
    onSignUpClick: () -> Unit,
    onSignInClick: () -> Unit
) {
    IntroScreenContent(
        onEvent = { event ->
            when(event) {
                IntroEvent.OnSignInClick -> onSignInClick()
                IntroEvent.OnSignUpClick -> onSignUpClick()
            }
        }
    )
}

@Composable
fun IntroScreenContent(
    onEvent: (IntroEvent) -> Unit
) {
    GradientBackground {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            RuniqueLogoVertical()
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .padding(bottom = 48.dp)
        ) {
            Text(
                text = stringResource(id = R.string.welcome_to_runique),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(id = R.string.runique_description),
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(32.dp))
            RuniqueOutlinedButton(
                text = stringResource(id = R.string.sign_in),
                isLoading = false,
                onClick = {
                    onEvent(IntroEvent.OnSignInClick)
                },
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            RuniqueButton(
                text = stringResource(id = R.string.sign_up),
                isLoading = false,
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    onEvent(IntroEvent.OnSignUpClick)
                }
            )
        }
    }
}



@Preview
@Composable
private fun IntroScreenContentPreview() {
    RuniqueTheme {
        IntroScreenContent(
            onEvent = {}
        )
    }
}