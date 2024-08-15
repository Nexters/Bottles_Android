package com.team.bottles.network.datasource

import com.team.bottles.network.dto.bottle.reponse.BottlePingPongResponse
import com.team.bottles.network.dto.bottle.reponse.PingPongListResponse
import com.team.bottles.network.dto.bottle.request.RegisterLetterRequest

interface BottleDataSource {

    suspend fun fetchPingPongList(): PingPongListResponse

    suspend fun fetchPingPongDetail(bottleId: Int): BottlePingPongResponse

    suspend fun sendPingPongLetter(
        bottleId: Int,
        request: RegisterLetterRequest
    )

}