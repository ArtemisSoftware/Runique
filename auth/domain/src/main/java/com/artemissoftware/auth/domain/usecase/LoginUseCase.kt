package com.artemissoftware.auth.domain.usecase

import com.artemissoftware.auth.domain.repository.AuthRepository
import com.artemissoftware.core.domain.models.UserAuthenticationInfo
import com.artemissoftware.core.domain.repository.SessionRepository
import com.artemissoftware.core.domain.util.DataError
import com.artemissoftware.core.domain.util.EmptyDataResult
import com.artemissoftware.core.domain.util.Result
import com.artemissoftware.core.domain.util.asEmptyDataResult

class LoginUseCase(
    private val authRepository: AuthRepository,
    private val sessionRepository: SessionRepository
) {
    suspend operator fun invoke(email: String, password: String): EmptyDataResult<DataError.Network> {
        val result = authRepository.login(email = email, password = password)

        if(result is Result.Success) {
            sessionRepository.set(info = result.data)
        }

        return result.asEmptyDataResult()
    }
}