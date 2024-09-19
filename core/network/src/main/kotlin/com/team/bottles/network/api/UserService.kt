package com.team.bottles.network.api

import com.team.bottles.network.dto.auth.request.BlockContactListRequest
import com.team.bottles.network.dto.user.request.AlimyOnOffRequest
import com.team.bottles.network.dto.user.request.ReportUserRequest
import com.team.bottles.network.dto.user.response.AlimyResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserService {

    @POST("/api/v1/user/report")
    suspend fun postReportUser(
        @Body request: ReportUserRequest
    )

    @POST("/api/v1/user/block/contact-list")
    suspend fun postBlockedContacts(
        @Body request: BlockContactListRequest
    )

    @GET("/api/v1/user/alimy")
    suspend fun getSettingNotifications(): List<AlimyResponse>

    @POST("/api/v1/user/alimy")
    suspend fun postSettingNotification(
        @Body request: AlimyOnOffRequest
    )

}