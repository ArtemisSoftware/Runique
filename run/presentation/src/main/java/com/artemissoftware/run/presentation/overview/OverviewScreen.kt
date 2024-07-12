@file:OptIn(ExperimentalMaterial3Api::class)

package com.artemissoftware.run.presentation.overview

import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artemissoftware.core.presentation.designsystem.AnalyticsIcon
import com.artemissoftware.core.presentation.designsystem.LogoIcon
import com.artemissoftware.core.presentation.designsystem.LogoutIcon
import com.artemissoftware.core.presentation.designsystem.RunIcon
import com.artemissoftware.core.presentation.designsystem.RuniqueTheme
import com.artemissoftware.core.presentation.designsystem.composables.buttons.RuniqueFloatingActionButton
import com.artemissoftware.core.presentation.designsystem.composables.scaffold.RuniqueScaffold
import com.artemissoftware.core.presentation.designsystem.composables.toolbar.DropDownItem
import com.artemissoftware.core.presentation.designsystem.composables.toolbar.RuniqueToolbar
import com.artemissoftware.run.presentation.R
import org.koin.androidx.compose.koinViewModel

@Composable
fun OverviewScreen(
    onStartRunClick: () -> Unit,
    viewModel: OverviewViewModel = koinViewModel(),
) {
    OverviewScreenContent(
        onEvent = { event ->
            when(event){
                OverviewEvent.OnStartClick -> onStartRunClick()
                else -> Unit
            }
            viewModel.onTriggerEvent(event)
        }
    )
}

@Composable
private fun OverviewScreenContent(
    onEvent: (OverviewEvent) -> Unit
) {
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
        state = topAppBarState
    )
    RuniqueScaffold(
        topAppBar = {
            RuniqueToolbar(
                showBackButton = false,
                title = stringResource(id = R.string.runique),
                scrollBehavior = scrollBehavior,
                menuItems = listOf(
                    DropDownItem(
                        icon = AnalyticsIcon,
                        title = stringResource(id = R.string.analytics)
                    ),
                    DropDownItem(
                        icon = LogoutIcon,
                        title = stringResource(id = R.string.logout)
                    ),
                ),
                onMenuItemClick = { index ->
                    when (index) {
                        0 -> onEvent(OverviewEvent.OnAnalyticsClick)
                        1 -> onEvent(OverviewEvent.OnLogoutClick)
                    }
                },
                startContent = {
                    Icon(
                        imageVector = LogoIcon,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(30.dp)
                    )
                }
            )
        },
        floatingActionButton = {
            RuniqueFloatingActionButton(
                icon = RunIcon,
                onClick = {
                    onEvent(OverviewEvent.OnStartClick)
                }
            )
        }
    ) { padding ->

    }
}

@Preview
@Composable
private fun OverviewScreenContentPreview() {
    RuniqueTheme {
        OverviewScreenContent(
            onEvent = {}
        )
    }
}