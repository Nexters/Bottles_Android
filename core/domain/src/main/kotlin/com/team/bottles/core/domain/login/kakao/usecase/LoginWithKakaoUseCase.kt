package com.team.bottles.core.domain.login.kakao.usecase

import com.team.bottles.core.domain.login.LoginRepository
import com.team.bottles.core.domain.login.ThirdPartyAccessToken
import javax.inject.Inject

class LoginWithKakaoUseCaseImpl @Inject constructor(
    private val loginRepository: LoginRepository
) : LoginWithKakaoUseCase {

    override suspend operator fun invoke(accessToken: ThirdPartyAccessToken) {
        loginRepository.kakaoLogin(accessToken = accessToken)
    }

}

interface LoginWithKakaoUseCase {

    suspend operator fun invoke(accessToken: ThirdPartyAccessToken)

}