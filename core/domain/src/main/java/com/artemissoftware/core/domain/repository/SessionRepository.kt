package com.artemissoftware.core.domain.repository

import com.artemissoftware.core.domain.models.UserAuthenticationInfo

interface SessionRepository {
    suspend fun get(): UserAuthenticationInfo?
    suspend fun set(info: UserAuthenticationInfo?)
}