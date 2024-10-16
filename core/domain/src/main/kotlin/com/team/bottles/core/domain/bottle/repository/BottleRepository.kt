package com.team.bottles.core.domain.bottle.repository

import com.team.bottles.core.domain.bottle.model.ArrivedBottle
import com.team.bottles.core.domain.bottle.model.PingPongBottle
import com.team.bottles.core.domain.bottle.model.PingPongDetail

interface BottleRepository {

    suspend fun loadPingPongList(): List<PingPongBottle>

    suspend fun loadPingPongDetail(bottleId: Int): PingPongDetail

    suspend fun sendPingPongLetter(
        bottleId: Int,
        letterOrder: Int,
        answer: String
    )

    suspend fun selectPingPongSharePhoto(bottleId: Int, willShare: Boolean)

    suspend fun selectPingPongShareKakaoId(bottleId: Int, willMatch: Boolean)

    suspend fun stopPingPong(bottleId: Int)

    suspend fun loadBottleList(): ArrivedBottle

    suspend fun updatePingPongReadStatus(bottleId: Int)

}