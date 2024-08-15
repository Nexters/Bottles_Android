package com.team.bottles.network.api

import com.team.bottles.network.dto.bottle.reponse.BottlePingPongResponse
import com.team.bottles.network.dto.bottle.reponse.PingPongListResponse
import com.team.bottles.network.dto.bottle.request.BottleImageShareRequest
import com.team.bottles.network.dto.bottle.request.BottleMatchRequest
import com.team.bottles.network.dto.bottle.request.RegisterLetterRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface BottleService {

    @GET("/api/v1/bottles/ping-pong")
    suspend fun getPingPongList() : PingPongListResponse

    @GET("/api/v1/bottles/ping-pong/{bottleId}")
    suspend fun getPingPongDetail(
        @Path("bottleId") bottleId: Int
    ) : BottlePingPongResponse

    @POST("/api/v1/bottles/ping-pong/{bottleId}/letters")
    suspend fun postPingPongLetter(
        @Path("bottleId") bottleId: Int,
        @Body request: RegisterLetterRequest
    )

    @POST("/api/v1/bottles/ping-pong/{bottleId}/image")
    suspend fun postPingPongImageShare(
        @Path("bottleId") bottleId: Int,
        @Body request: BottleImageShareRequest
    )

    @POST("/api/v1/bottles/ping-pong/{bottleId}/match")
    suspend fun postPingPongKakaoIdShare(
        @Path("bottleId") bottleId: Int,
        @Body request: BottleMatchRequest
    )

    @POST("/api/v1/bottles/ping-pong/{bottleId}/stop")
    suspend fun postPingPongStop(
        @Path("bottleId") bottleId: Int,
    )

    @POST("/api/v1/bottles/ping-pong/{bottleId}/read")
    suspend fun postPingPongRead(
        @Path("bottleId") bottleId: Int,
    )

}