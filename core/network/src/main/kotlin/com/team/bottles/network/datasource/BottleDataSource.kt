package com.team.bottles.network.datasource

import com.team.bottles.network.dto.bottle.reponse.BottlePingPongResponse
import com.team.bottles.network.dto.bottle.reponse.PingPongListResponse

interface BottleDataSource {

    suspend fun fetchPingPongList(): PingPongListResponse

    suspend fun fetchPingPongDetail(bottleId: Int): BottlePingPongResponse

}