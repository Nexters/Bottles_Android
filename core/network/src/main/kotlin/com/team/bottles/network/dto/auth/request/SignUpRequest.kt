package com.team.bottles.network.dto.auth.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignUpRequest(
    @SerialName("name") val name: String,
    @SerialName("birthYear") val birthYear: Int,
    @SerialName("birthMonth") val birthMonth: Int,
    @SerialName("birthDay") val birthDay: Int,
    @SerialName("gender") val gender: Gender,
    @SerialName("phoneNumber") val phoneNumber: String,
    @SerialName("authCode") val authCode: String
)

@Serializable
enum class Gender {
    @SerialName("FEMALE") FEMALE,
    @SerialName("MALE") MALE,
    ;
}