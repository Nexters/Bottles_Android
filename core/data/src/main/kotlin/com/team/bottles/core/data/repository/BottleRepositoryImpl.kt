package com.team.bottles.core.data.repository

import com.team.bottles.core.data.mapper.toPingPongResult
import com.team.bottles.core.domain.bottle.model.PingPongResult
import com.team.bottles.core.domain.bottle.repository.BottleRepository
import com.team.bottles.network.datasource.BottleDataSource
import javax.inject.Inject

class BottleRepositoryImpl @Inject constructor(
    private val bottleDataSource: BottleDataSource
) : BottleRepository {

    override suspend fun loadPingPongList(): PingPongResult =
        bottleDataSource.fetchPingPongList().toPingPongResult()

}