package com.team.bottles.core.data.datasource.remote

import com.team.bottles.network.dto.request.KakaoSignInUpRequest
import com.team.bottles.network.dto.response.KakaoSignInUpResponse

interface AuthDataSource {

    suspend fun authenticateWithKakao(
        request: KakaoSignInUpRequest
    ): KakaoSignInUpResponse

}