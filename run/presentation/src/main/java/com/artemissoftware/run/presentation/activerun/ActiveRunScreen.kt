@file:OptIn(ExperimentalMaterial3Api::class)

package com.artemissoftware.run.presentation.activerun

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artemissoftware.core.presentation.designsystem.RuniqueTheme
import com.artemissoftware.core.presentation.designsystem.StartIcon
import com.artemissoftware.core.presentation.designsystem.StopIcon
import com.artemissoftware.core.presentation.designsystem.composables.buttons.RuniqueFloatingActionButton
import com.artemissoftware.core.presentation.designsystem.composables.scaffold.RuniqueScaffold
import com.artemissoftware.core.presentation.designsystem.composables.toolbar.RuniqueToolbar
import com.artemissoftware.run.presentation.R
import com.artemissoftware.run.presentation.activerun.composables.RunDataCard
import org.koin.androidx.compose.koinViewModel

@Composable
fun ActiveRunScreen(
    viewModel: ActiveRunViewModel = koinViewModel(),
) {
    ActiveRunScreenContent(
        state = viewModel.state,
        onEvent = viewModel::onTriggerEvent
    )
}

@Composable
private fun ActiveRunScreenContent(
    state: ActiveRunState,
    onEvent: (ActiveRunEvent) -> Unit
) {
    RuniqueScaffold(
        withGradient = false,
        topAppBar = {
            RuniqueToolbar(
                showBackButton = true,
                title = stringResource(id = R.string.active_run),
                onBackClick = {
                    onEvent(ActiveRunEvent.OnBackClick)
                },
            )
        },
        floatingActionButton = {
            RuniqueFloatingActionButton(
                icon = if (state.shouldTrack) {
                    StopIcon
                } else {
                    StartIcon
                },
                onClick = {
                    onEvent(ActiveRunEvent.OnToggleRunClick)
                },
                iconSize = 20.dp,
                contentDescription = if(state.shouldTrack) {
                    stringResource(id = R.string.pause_run)
                } else {
                    stringResource(id = R.string.start_run)
                }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
        ) {
            RunDataCard(
                elapsedTime = state.elapsedTime,
                runData = state.runData,
                modifier = Modifier
                    .padding(16.dp)
                    .padding(padding)
                    .fillMaxWidth()
            )
        }
    }
}

@Preview
@Composable
private fun ActiveRunScreenContentPreview() {
    RuniqueTheme {
        ActiveRunScreenContent(
            state = ActiveRunState(),
            onEvent = {}
        )
    }
}