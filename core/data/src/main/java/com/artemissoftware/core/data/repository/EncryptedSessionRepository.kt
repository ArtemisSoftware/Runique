package com.artemissoftware.core.data.repository

import android.content.SharedPreferences
import com.artemissoftware.core.data.mapper.toInfo
import com.artemissoftware.core.data.mapper.toSerializable
import com.artemissoftware.core.data.serializable.UserAuthenticationInfoSerializable
import com.artemissoftware.core.domain.models.UserAuthenticationInfo
import com.artemissoftware.core.domain.repository.SessionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class EncryptedSessionRepository(
    private val sharedPreferences: SharedPreferences
): SessionRepository {

    override suspend fun get(): UserAuthenticationInfo? {
        return withContext(Dispatchers.IO) {
            val json = sharedPreferences.getString(KEY_AUTH_INFO, null)
            json?.let {
                Json.decodeFromString<UserAuthenticationInfoSerializable>(it).toInfo()
            }
        }
    }

    override suspend fun set(info: UserAuthenticationInfo?) {
        withContext(Dispatchers.IO) {
            if(info == null) {
                sharedPreferences.edit().remove(KEY_AUTH_INFO).apply()
                return@withContext
            }

            val json = Json.encodeToString(info.toSerializable())
            sharedPreferences
                .edit()
                .putString(KEY_AUTH_INFO, json)
                .commit()
        }
    }

    companion object {
        private const val KEY_AUTH_INFO = "KEY_AUTH_INFO"
    }
}