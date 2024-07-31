package com.team.bottles.core.data.model

import com.team.bottles.core.domain.auth.model.AuthResult
import com.team.bottles.network.dto.response.KakaoSignInUpResponse

fun KakaoSignInUpResponse.toAuthResult(): AuthResult =
    AuthResult(isSignUp = this.isSignUp)