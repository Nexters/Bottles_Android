package com.team.bottles.core.domain.bottle.model

data class PingPongLetter(
    val canShow: Boolean = false,
    val isDone: Boolean = false,
    val myAnswer: String = "",
    val order: Int = 0,
    val question: String = "",
    val otherAnswer: String = "",
    val shouldAnswer: Boolean = true,
)