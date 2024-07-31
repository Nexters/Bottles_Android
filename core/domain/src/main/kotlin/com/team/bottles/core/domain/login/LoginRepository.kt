package com.team.bottles.core.domain.login

interface LoginRepository {

    suspend fun kakaoLogin(accessToken: ThirdPartyAccessToken)

}