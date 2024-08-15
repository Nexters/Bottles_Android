package com.team.bottles.core.data.repository

import com.team.bottles.core.data.mapper.toPingPongDetail
import com.team.bottles.core.data.mapper.toPingPongResult
import com.team.bottles.core.domain.bottle.model.PingPongDetail
import com.team.bottles.core.domain.bottle.model.PingPongList
import com.team.bottles.core.domain.bottle.repository.BottleRepository
import com.team.bottles.network.datasource.BottleDataSource
import com.team.bottles.network.dto.bottle.request.BottleImageShareRequest
import com.team.bottles.network.dto.bottle.request.BottleMatchRequest
import com.team.bottles.network.dto.bottle.request.RegisterLetterRequest
import javax.inject.Inject

class BottleRepositoryImpl @Inject constructor(
    private val bottleDataSource: BottleDataSource
) : BottleRepository {

    override suspend fun loadPingPongList(): PingPongList =
        bottleDataSource.fetchPingPongList().toPingPongResult()

    override suspend fun loadPingPongDetail(bottleId: Int): PingPongDetail =
        bottleDataSource.fetchPingPongDetail(bottleId = bottleId).toPingPongDetail()

    override suspend fun sendPingPongLetter(bottleId: Int, letterOrder: Int, answer: String) {
        bottleDataSource.sendPingPongLetter(
            bottleId = bottleId,
            request = RegisterLetterRequest(
                order = letterOrder,
                answer = answer)
        )
    }

    override suspend fun selectPingPongSharePhoto(bottleId: Int, willShare: Boolean) {
        bottleDataSource.insertPingPongSharePhoto(
            bottleId = bottleId,
            request = BottleImageShareRequest(
                willShare = willShare
            )
        )
    }

    override suspend fun selectPingPongShareKakaoId(bottleId: Int, willMatch: Boolean) {
        bottleDataSource.insertPingPongShareKakaoId(
            bottleId = bottleId,
            request = BottleMatchRequest(
                willMatch = willMatch
            )
        )
    }

    override suspend fun stopPingPong(bottleId: Int) {
        bottleDataSource.deletePingPong(bottleId = bottleId)
    }

}