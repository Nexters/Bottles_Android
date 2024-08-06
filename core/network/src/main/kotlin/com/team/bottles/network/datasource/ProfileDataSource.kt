package com.team.bottles.network.datasource

import com.team.bottles.network.dto.profile.request.RegisterIntroductionRequest

interface ProfileDataSource {

    suspend fun createIntroduction(
        request: RegisterIntroductionRequest
    )

}