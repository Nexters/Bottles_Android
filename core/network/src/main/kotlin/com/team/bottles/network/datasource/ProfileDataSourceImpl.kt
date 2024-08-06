package com.team.bottles.network.datasource

import com.team.bottles.network.api.ProfileService
import com.team.bottles.network.dto.profile.request.RegisterIntroductionRequest
import javax.inject.Inject

class ProfileDataSourceImpl @Inject constructor(
    private val profileService: ProfileService,
) : ProfileDataSource {

    override suspend fun createIntroduction(request: RegisterIntroductionRequest) {
        profileService.createIntroduction(registerIntroductionRequest = request)
    }

}