package com.artemissoftware.auth.domain.repository

import com.artemissoftware.core.domain.util.DataError
import com.artemissoftware.core.domain.util.EmptyDataResult

interface AuthRepository {
    suspend fun register(email: String, password: String): EmptyDataResult<DataError.Network>
}