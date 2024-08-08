package com.team.bottles.core.domain.bottle.model

data class PingPongResult(
    val activeBottles: List<PingPongBottle>,
    val doneBottles: List<PingPongBottle>
)

data class PingPongBottle(
    val id: Long,
    val isRead: Boolean,
    val userName: String,
    val age: Int,
    val mbti: String,
    val keyword: List<String>,
    val userImageUrl: String,
)