package com.team.bottles.core.data.mapper

import com.team.bottles.core.domain.auth.model.AuthResult
import com.team.bottles.network.dto.auth.response.KakaoSignInUpResponse

fun KakaoSignInUpResponse.toAuthResult(): AuthResult =
    AuthResult(
        isSignUp = this.isSignUp,
        hasCompleteIntroduction = this.hasCompleteIntroduction,
        hasCompleteUserProfile = this.hasCompleteUserProfile
    )