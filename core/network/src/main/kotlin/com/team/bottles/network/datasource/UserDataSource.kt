package com.team.bottles.network.datasource

import com.team.bottles.network.dto.user.request.ReportUserRequest

interface UserDataSource {

    suspend fun sendReportContents(request: ReportUserRequest)

}