package com.team.bottles.network.datasource

import com.team.bottles.network.dto.profile.reponse.PingPongListResponse

interface BottleDataSource {

    suspend fun fetchPingPongList(): PingPongListResponse

}