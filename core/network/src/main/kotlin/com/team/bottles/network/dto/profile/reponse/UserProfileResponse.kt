package com.team.bottles.network.dto.profile.reponse

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserProfileResponse(
    @SerialName("userName") val userName: String,
    @SerialName("age") val age: Int,
    @SerialName("blockedUserCount") val blockedUserCount: Int,
    @SerialName("imageUrl") val imageUrl: String?,
    @SerialName("introduction") val introduction: List<QuestionAndAnswerDTO>,
    @SerialName("profileSelect") val profileSelect: UserProfileSelectDTO?,
)

@Serializable
data class UserProfileSelectDTO(
    @SerialName("mbti") val mbti: String,
    @SerialName("keyword") val keyword: List<String>,
    @SerialName("interest") val interest: InterestDto,
    @SerialName("job") val job: String,
    @SerialName("height") val height: Int,
    @SerialName("smoking") val smoking: String,
    @SerialName("alcohol") val alcohol: String,
    @SerialName("religion") val religion: String,
    @SerialName("region") val region: RegionDto,
)

@Serializable
data class QuestionAndAnswerDTO(
    @SerialName("question") val question: String,
    @SerialName("answer") val answer: String,
)

@Serializable
data class InterestDto(
    @SerialName("culture") val culture: List<String>,
    @SerialName("sports") val sports: List<String>,
    @SerialName("entertainment") val entertainment: List<String>,
    @SerialName("etc") val etc: List<String>,
)

@Serializable
data class RegionDto(
    @SerialName("city") val city: String,
    @SerialName("state") val state: String
)