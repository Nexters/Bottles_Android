package com.team.bottles.core.domain.auth.model

data class AuthResult(
    val isSignUp: Boolean,
    val hasCompleteIntroduction: Boolean
)