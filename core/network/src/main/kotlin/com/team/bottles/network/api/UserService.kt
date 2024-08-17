package com.team.bottles.network.api

import com.team.bottles.network.dto.user.request.ReportUserRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {

    @POST("/api/v1/user/report")
    suspend fun postReportUser(
        @Body request: ReportUserRequest
    )

}