package com.team.bottles.core.domain.auth.model

import kotlinx.serialization.Serializable

@Serializable
data class Token(
    val accessToken: String,
    val refreshToken: String
)