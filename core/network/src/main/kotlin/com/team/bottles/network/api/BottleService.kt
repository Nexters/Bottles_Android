package com.team.bottles.network.api

import com.team.bottles.network.dto.bottle.reponse.PingPongListResponse
import retrofit2.http.GET

interface BottleService {

    @GET("/api/v1/bottles/ping-pong")
    suspend fun getPingPongList(): PingPongListResponse

}