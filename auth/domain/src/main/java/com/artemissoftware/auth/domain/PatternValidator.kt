package com.artemissoftware.auth.domain

interface PatternValidator {
    fun matches(value: String): Boolean
}