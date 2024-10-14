package com.team.bottles.core.domain.bottle.model

data class PingPongBottle(
    val id: Long,
    val userId: Long,
    val isRead: Boolean,
    val userName: String,
    val age: Int,
    val mbti: String,
    val keyword: List<String>,
    val userImageUrl: String,
    val lastActivatedAt: String,
    val lastStatus: String
)