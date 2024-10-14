package com.team.bottles.core.data.mapper

import com.team.bottles.core.domain.bottle.model.PingPongBottle
import com.team.bottles.network.dto.bottle.reponse.PingPongBottleDto
import com.team.bottles.network.dto.bottle.reponse.PingPongListResponse

fun PingPongListResponse.toPingPongResult(): List<PingPongBottle> =
    this.pingPongBottles.map { pingPongBottle ->
        pingPongBottle.toPingPongBottle()
    }

fun PingPongBottleDto.toPingPongBottle(): PingPongBottle =
    PingPongBottle(
        id = this.id,
        userId = this.userId,
        isRead = this.isRead,
        userName = this.userName,
        age = this.age,
        mbti = this.mbti?: "",
        keyword = this.keyword?: emptyList(),
        userImageUrl = this.userImageUrl?: "",
        lastActivatedAt = this.lastActivatedAt,
        lastStatus = this.lastStatus.status
    )