package com.team.bottles.network.datasource

import com.team.bottles.network.dto.bottle.reponse.BottleListResponse
import com.team.bottles.network.dto.bottle.reponse.BottlePingPongResponse
import com.team.bottles.network.dto.bottle.reponse.PingPongListResponse
import com.team.bottles.network.dto.bottle.request.BottleImageShareRequest
import com.team.bottles.network.dto.bottle.request.BottleMatchRequest
import com.team.bottles.network.dto.bottle.request.RegisterLetterRequest

interface BottleDataSource {

    suspend fun fetchPingPongList(): PingPongListResponse

    suspend fun fetchPingPongDetail(bottleId: Int): BottlePingPongResponse

    suspend fun sendPingPongLetter(
        bottleId: Int,
        request: RegisterLetterRequest
    )

    suspend fun insertPingPongSharePhoto(
        bottleId: Int,
        request: BottleImageShareRequest
    )

    suspend fun insertPingPongShareKakaoId(
        bottleId: Int,
        request: BottleMatchRequest
    )

    suspend fun deletePingPong(bottleId: Int)

    suspend fun updatePingPongReadStatus(bottleId: Int)

    suspend fun fetchBottleList(): BottleListResponse

}