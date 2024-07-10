package com.artemissoftware.core.data.mapper

import com.artemissoftware.core.data.serializable.UserAuthenticationInfoSerializable
import com.artemissoftware.core.domain.models.UserAuthenticationInfo

fun UserAuthenticationInfo.toSerializable(): UserAuthenticationInfoSerializable {
    return UserAuthenticationInfoSerializable(
        accessToken = accessToken,
        refreshToken = refreshToken,
        userId = userId
    )
}

fun UserAuthenticationInfoSerializable.toInfo(): UserAuthenticationInfo {
    return UserAuthenticationInfo(
        accessToken = accessToken,
        refreshToken = refreshToken,
        userId = userId
    )
}