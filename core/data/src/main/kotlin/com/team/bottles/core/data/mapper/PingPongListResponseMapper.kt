package com.team.bottles.core.data.mapper

import com.team.bottles.core.domain.bottle.model.PingPongBottle
import com.team.bottles.core.domain.bottle.model.PingPongResult
import com.team.bottles.network.dto.bottle.reponse.PingPongBottleDto
import com.team.bottles.network.dto.bottle.reponse.PingPongListResponse

fun PingPongListResponse.toPingPongResult(): PingPongResult =
    PingPongResult(
        activeBottles = this.activeBottles.map { dto -> dto.toPingPongBottle() },
        doneBottles = this.doneBottles.map { dto -> dto.toPingPongBottle() }
    )

fun PingPongBottleDto.toPingPongBottle(): PingPongBottle =
    PingPongBottle(
        id = this.id,
        userId = this.userId,
        isRead = this.isRead,
        userName = this.userName,
        age = this.age,
        mbti = this.mbti?: "",
        keyword = this.keyword?: emptyList(),
        userImageUrl = this.userImageUrl?: ""
    )