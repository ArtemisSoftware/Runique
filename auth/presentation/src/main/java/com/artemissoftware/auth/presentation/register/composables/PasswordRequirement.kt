package com.artemissoftware.auth.presentation.register.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.artemissoftware.core.presentation.designsystem.CheckIcon
import com.artemissoftware.core.presentation.designsystem.CrossIcon
import com.artemissoftware.core.presentation.designsystem.RuniqueDarkRed
import com.artemissoftware.core.presentation.designsystem.RuniqueGreen
import com.artemissoftware.core.presentation.designsystem.RuniqueTheme

@Composable
internal fun PasswordRequirement(
    text: String,
    isValid: Boolean,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = if (isValid) {
                CheckIcon
            } else {
                CrossIcon
            },
            contentDescription = null,
            tint = if(isValid) RuniqueGreen else RuniqueDarkRed
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 14.sp
        )
    }
}

@Preview
@Composable
private fun PasswordRequirementPreview() {
    RuniqueTheme {
        PasswordRequirement(
            text = "My password",
            isValid = true,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
private fun PasswordRequirementNotValidPreview() {
    RuniqueTheme {
        PasswordRequirement(
            text = "My password",
            isValid = false,
            modifier = Modifier.fillMaxWidth()
        )
    }
}