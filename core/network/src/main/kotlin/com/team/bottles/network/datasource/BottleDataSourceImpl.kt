package com.team.bottles.network.datasource

import com.team.bottles.network.api.BottleService
import com.team.bottles.network.dto.bottle.reponse.BottlePingPongResponse
import com.team.bottles.network.dto.bottle.reponse.PingPongListResponse
import com.team.bottles.network.dto.bottle.request.BottleImageShareRequest
import com.team.bottles.network.dto.bottle.request.RegisterLetterRequest
import javax.inject.Inject

class BottleDataSourceImpl @Inject constructor(
    private val bottleService: BottleService,
): BottleDataSource {

    override suspend fun fetchPingPongList(): PingPongListResponse =
        bottleService.getPingPongList()

    override suspend fun fetchPingPongDetail(bottleId: Int): BottlePingPongResponse =
        bottleService.getPingPongDetail(bottleId = bottleId)

    override suspend fun sendPingPongLetter(bottleId: Int, request: RegisterLetterRequest) {
        bottleService.postPingPongLetter(bottleId = bottleId, request = request)
    }

    override suspend fun insertPingPongSharePhoto(bottleId: Int, request: BottleImageShareRequest) {
        bottleService.postPingPongImageShare(bottleId = bottleId, request = request)
    }

}