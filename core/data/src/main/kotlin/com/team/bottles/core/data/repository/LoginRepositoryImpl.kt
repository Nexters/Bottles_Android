package com.team.bottles.core.data.repository

import com.team.bottles.core.domain.login.LoginRepository
import com.team.bottles.core.domain.login.ThirdPartyAccessToken
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(

): LoginRepository {

    override suspend fun kakaoLogin(accessToken: ThirdPartyAccessToken) {
        TODO("Not yet implemented")
    }

}