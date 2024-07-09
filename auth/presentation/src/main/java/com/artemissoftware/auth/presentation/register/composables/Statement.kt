package com.artemissoftware.auth.presentation.register.composables

import androidx.annotation.StringRes
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import com.artemissoftware.auth.presentation.R
import com.artemissoftware.core.presentation.designsystem.Poppins
import com.artemissoftware.core.presentation.designsystem.RuniqueGray
import com.artemissoftware.core.presentation.designsystem.RuniqueTheme

@Composable
fun Statement(
    @StringRes text: Int,
    @StringRes textToClick: Int,
    onClick: () -> Unit
) {
    val annotatedString = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                fontFamily = Poppins,
                color = RuniqueGray
            )
        ) {
            append(stringResource(id = R.string.already_have_an_account) + " ")
            pushStringAnnotation(
                tag = "clickable_text",
                annotation = stringResource(id = R.string.login)
            )
            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary,
                    fontFamily = Poppins
                )
            ) {
                append(stringResource(id = R.string.login))
            }
        }
    }
    ClickableText(
        text = annotatedString,
        onClick = { offset ->
            annotatedString.getStringAnnotations(
                tag = "clickable_text",
                start = offset,
                end = offset
            ).firstOrNull()?.let {
                onClick()
            }
        }
    )
}

@Preview
@Composable
private fun StatementPreview() {
    RuniqueTheme {
        Statement(
            text = R.string.already_have_an_account,
            textToClick = R.string.login,
            onClick = {}
        )
    }
}
