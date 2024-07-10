package com.artemissoftware.core.domain.models

data class UserAuthenticationInfo(
    val accessToken: String,
    val refreshToken: String,
    val userId: String
)
