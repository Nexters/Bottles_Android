package com.team.bottles.core.domain.bottle.repository

import com.team.bottles.core.domain.bottle.model.PingPongResult

interface BottleRepository {

    suspend fun loadPingPongList(): PingPongResult

}