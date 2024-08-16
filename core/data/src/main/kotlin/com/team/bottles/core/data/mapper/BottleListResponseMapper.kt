package com.team.bottles.core.data.mapper

import com.team.bottles.core.domain.bottle.model.ArrivedBottle
import com.team.bottles.core.domain.bottle.model.Bottle
import com.team.bottles.network.dto.bottle.reponse.BottleDto
import com.team.bottles.network.dto.bottle.reponse.BottleListResponse

fun BottleListResponse.toArrivedBottle(): ArrivedBottle =
    ArrivedBottle(
        randomBottles = this.randomBottles.map { it.toBottle() },
        sentBottles = this.sentBottles.map { it.toBottle() },
        nextBottleLeftHours = this.nextBottleLeftHours
    )

fun BottleDto.toBottle(): Bottle =
    Bottle(
        id = this.id,
        userId = this.userId,
        userName = this.userName,
        age = this.age,
        mbti = this.mbti?: "",
        keyword = this.keyword?: emptyList(),
        userImageUrl = this.userImageUrl?: "",
        expiredAt = this.expiredAt
    )