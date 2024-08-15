package com.team.bottles.core.domain.bottle.repository

import com.team.bottles.core.domain.bottle.model.PingPongList

interface BottleRepository {

    suspend fun loadPingPongList(): PingPongList

}