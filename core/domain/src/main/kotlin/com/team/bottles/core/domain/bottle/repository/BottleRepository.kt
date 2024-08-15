package com.team.bottles.core.domain.bottle.repository

import com.team.bottles.core.domain.bottle.model.PingPongDetail
import com.team.bottles.core.domain.bottle.model.PingPongList

interface BottleRepository {

    suspend fun loadPingPongList(): PingPongList

    suspend fun loadPingPongDetail(bottleId: Int): PingPongDetail

    suspend fun sendPingPongLetter(
        bottleId: Int,
        letterOrder: Int,
        answer: String
    )

    suspend fun selectPingPongSharePhoto(bottleId: Int, willShare: Boolean)

}