package com.team.bottles.core.domain.bottle.model

data class ArrivedBottle(
    val randomBottles: List<Bottle>,
    val sentBottles: List<Bottle>,
    val nextBottleLeftHours: Int,
)

data class Bottle(
    val id: Long,
    val userId: Long,
    val userName: String,
    val age: Int,
    val mbti: String,
    val keyword: List<String>,
    val userImageUrl: String,
    val expiredAt: String
)