package com.team.bottles.network.api

import com.team.bottles.network.dto.bottle.reponse.BottlePingPongResponse
import com.team.bottles.network.dto.bottle.reponse.PingPongListResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface BottleService {

    @GET("/api/v1/bottles/ping-pong")
    suspend fun getPingPongList() : PingPongListResponse

    @GET("/api/v1/bottles/ping-pong/{bottleId}")
    suspend fun getPingPongDetail(
        @Path("bottleId") bottleId: Int
    ) : BottlePingPongResponse

}