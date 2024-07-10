package com.artemissoftware.auth.data.repository

import com.artemissoftware.auth.data.models.request.RegisterRequest
import com.artemissoftware.auth.domain.repository.AuthRepository
import com.artemissoftware.core.data.networking.post
import com.artemissoftware.core.domain.util.DataError
import com.artemissoftware.core.domain.util.EmptyDataResult
import io.ktor.client.HttpClient

class AuthRepositoryImpl(
    private val httpClient: HttpClient
): AuthRepository {

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