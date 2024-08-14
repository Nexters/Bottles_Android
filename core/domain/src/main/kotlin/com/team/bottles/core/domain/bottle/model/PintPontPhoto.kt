package com.team.bottles.core.domain.bottle.model

data class PingPongPhoto(
    val myImageUrl: String = "",
    val otherImageUrl: String = "",
    val shouldAnswer: Boolean = true,
    val myAnswer: Boolean = false,
    val otherAnswer: Boolean = false,
    val isDone: Boolean = false,
)