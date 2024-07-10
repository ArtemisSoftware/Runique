package com.artemissoftware.auth.domain.repository

import com.artemissoftware.core.domain.models.UserAuthenticationInfo
import com.artemissoftware.core.domain.util.DataError
import com.artemissoftware.core.domain.util.EmptyDataResult
import com.artemissoftware.core.domain.util.Result

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<UserAuthenticationInfo, DataError.Network>
    suspend fun register(email: String, password: String): EmptyDataResult<DataError.Network>
}