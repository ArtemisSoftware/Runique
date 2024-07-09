package com.artemissoftware.auth.presentation.intro

sealed interface IntroEvent {
    data object OnSignInClick: IntroEvent
    data object OnSignUpClick: IntroEvent
}