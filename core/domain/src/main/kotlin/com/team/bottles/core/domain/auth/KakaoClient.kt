package com.team.bottles.core.domain.auth

interface KakaoClient {

    suspend fun login(): KakaoClientResult

}