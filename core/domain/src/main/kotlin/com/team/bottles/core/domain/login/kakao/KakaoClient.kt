package com.team.bottles.core.domain.login.kakao

import com.team.bottles.core.domain.login.ThirdPartyAccessToken

interface KakaoClient {

    suspend fun login(): ThirdPartyAccessToken

}