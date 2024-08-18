package com.team.bottles.network.dto.profile.reponse

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserProfileStatusResponse(
    @SerialName("userProfileStatus") val userProfileStatus: UserProfileStatusDTO
)

@Serializable
enum class UserProfileStatusDTO {
    @SerialName("EMPTY") EMPTY,
    @SerialName("ONLY_PROFILE_CREATED") ONLY_PROFILE_CREATED,
    @SerialName("INTRODUCE_DONE") INTRODUCE_DONE,
    @SerialName("PHOTO_DONE") PHOTO_DONE,
    ;
}