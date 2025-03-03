package com.artemissoftware.core.presentation.designsystem.composables.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artemissoftware.core.presentation.designsystem.RuniqueBlack
import com.artemissoftware.core.presentation.designsystem.RuniqueGray
import com.artemissoftware.core.presentation.designsystem.RuniqueTheme

@Composable
fun RuniqueOutlinedButton(
    text: String,
    isLoading: Boolean,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    OutlinedButton(
        onClick = onClick,
        enabled = enabled,
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = MaterialTheme.colorScheme.onBackground,
        ),
        border = BorderStroke(
            width = 0.5.dp,
            color = MaterialTheme.colorScheme.onBackground
        ),
        shape = RoundedCornerShape(100f),
        modifier = modifier
            .height(IntrinsicSize.Min)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(15.dp)
                    .alpha(if (isLoading) 1f else 0f),
                strokeWidth = 1.5.dp,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = text,
                modifier = Modifier
                    .alpha(if(isLoading) 0f else 1f),
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Preview
@Composable
private fun RuniqueOutlinedButtonPreview() {
    RuniqueTheme {
        RuniqueOutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            text = "The button",
            isLoading = false,
            onClick = {}
        )
    }
}

@Preview
@Composable
private fun RuniqueOutlinedButtonDisabledPreview() {
    RuniqueTheme {
        RuniqueOutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            text = "The button",
            isLoading = false,
            enabled = false,
            onClick = {}
        )
    }
}

@Preview
@Composable
private fun RuniqueOutlinedButtonLoadingPreview() {
    RuniqueTheme {
        RuniqueOutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            text = "The button",
            isLoading = true,
            onClick = {}
        )
    }
}