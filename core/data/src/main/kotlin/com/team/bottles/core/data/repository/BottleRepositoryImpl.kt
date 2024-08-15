package com.team.bottles.core.data.repository

import com.team.bottles.core.data.mapper.toPingPongDetail
import com.team.bottles.core.data.mapper.toPingPongResult
import com.team.bottles.core.domain.bottle.model.PingPongDetail
import com.team.bottles.core.domain.bottle.model.PingPongList
import com.team.bottles.core.domain.bottle.repository.BottleRepository
import com.team.bottles.network.datasource.BottleDataSource
import javax.inject.Inject

class BottleRepositoryImpl @Inject constructor(
    private val bottleDataSource: BottleDataSource
) : BottleRepository {

    override suspend fun loadPingPongList(): PingPongList =
        bottleDataSource.fetchPingPongList().toPingPongResult()

    override suspend fun loadPingPongDetail(bottleId: Int): PingPongDetail =
        bottleDataSource.fetchPingPongDetail(bottleId = bottleId).toPingPongDetail()

}