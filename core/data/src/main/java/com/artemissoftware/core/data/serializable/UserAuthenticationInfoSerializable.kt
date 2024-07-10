package com.artemissoftware.core.data.serializable

import kotlinx.serialization.Serializable

@Serializable
data class UserAuthenticationInfoSerializable(
    val accessToken: String,
    val refreshToken: String,
    val userId: String
)
