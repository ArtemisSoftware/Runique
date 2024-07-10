package com.artemissoftware.auth.data.repository

import com.artemissoftware.auth.data.models.request.LoginRequest
import com.artemissoftware.auth.data.models.request.RegisterRequest
import com.artemissoftware.auth.data.models.response.LoginResponse
import com.artemissoftware.auth.domain.repository.AuthRepository
import com.artemissoftware.core.data.networking.post
import com.artemissoftware.core.domain.models.UserAuthenticationInfo
import com.artemissoftware.core.domain.util.DataError
import com.artemissoftware.core.domain.util.EmptyDataResult
import com.artemissoftware.core.domain.util.Result
import com.artemissoftware.core.domain.util.asEmptyDataResult
import io.ktor.client.HttpClient

class AuthRepositoryImpl(
    private val httpClient: HttpClient
): AuthRepository {
    override suspend fun login(
        email: String,
        password: String
    ): Result<UserAuthenticationInfo, DataError.Network> {
        val result = httpClient.post<LoginRequest, LoginResponse>(
            route = "/login",
            body = LoginRequest(
                email = email,
                password = password
            )
        )

        return when(result){
            is Result.Error -> Result.Error<DataError.Network>(result.error)
            is Result.Success -> Result.Success(
                UserAuthenticationInfo(
                    accessToken = result.data.accessToken,
                    refreshToken = result.data.refreshToken,
                    userId = result.data.userId
                )
            )
        }
    }

    override suspend fun register(email: String, password: String): EmptyDataResult<DataError.Network> {
        return httpClient.post<RegisterRequest, Unit>(
            route = "/register",
            body = RegisterRequest(
                email = email,
                password = password
            )
        )
    }
}