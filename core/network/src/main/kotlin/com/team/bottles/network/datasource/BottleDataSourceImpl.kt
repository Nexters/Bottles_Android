package com.team.bottles.network.datasource

import com.team.bottles.network.api.BottleService
import com.team.bottles.network.dto.bottle.reponse.BottlePingPongResponse
import com.team.bottles.network.dto.bottle.reponse.PingPongListResponse
import javax.inject.Inject

class BottleDataSourceImpl @Inject constructor(
    private val bottleService: BottleService,
): BottleDataSource {

    override suspend fun fetchPingPongList(): PingPongListResponse =
        bottleService.getPingPongList()

    override suspend fun fetchPingPongDetail(bottleId: Int): BottlePingPongResponse =
        bottleService.getPingPongDetail(bottleId = bottleId)

}