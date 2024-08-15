package com.team.bottles.network.dto.bottle.reponse

import com.team.bottles.network.dto.profile.reponse.QuestionAndAnswerDTO
import com.team.bottles.network.dto.profile.reponse.UserProfileSelectDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BottlePingPongResponse(
    @SerialName("isStopped") val isStopped: Boolean,
    @SerialName("stopUserName") val stopUserName: String?,
    @SerialName("deleteAfterDays") val deleteAfterDays: Long?,
    @SerialName("userProfile") val userProfile: PingPongUserProfileDTO,
    @SerialName("introduction") val introduction: List<QuestionAndAnswerDTO>?,
    @SerialName("letters") val letters: List<PingPongLetterDTO>,
    @SerialName("photo") val photo: PhotoDTO,
    @SerialName("matchResult") val matchResult: MatchResultDTO,
)

@Serializable
data class PingPongUserProfileDTO(
    @SerialName("userId") val userId: Long,
    @SerialName("userName") val userName: String,
    @SerialName("age") val age: Int,
    @SerialName("profileSelect") val profileSelect: UserProfileSelectDTO?,
    @SerialName("userImageUrl") val userImageUrl: String?,
)

@Serializable
data class PingPongLetterDTO(
    @SerialName("order") val order: Int,
    @SerialName("question") val question: String,
    @SerialName("canshow") val canshow: Boolean,
    @SerialName("myAnswer") val myAnswer: String?,
    @SerialName("otherAnswer") val otherAnswer: String?,
    @SerialName("shouldAnswer") val shouldAnswer: Boolean,
    @SerialName("isDone") val isDone: Boolean,
)

@Serializable
data class PhotoDTO(
    @SerialName("photoStatus") val photoStatus: PhotoStatusDTO,
    @SerialName("myImageUrl") val myImageUrl: String?,
    @SerialName("otherImageUrl") val otherImageUrl: String?,
    @SerialName("shouldAnswer") val shouldAnswer: Boolean,
    @SerialName("myAnswer") val myAnswer: Boolean?,
    @SerialName("otherAnswer") val otherAnswer: Boolean?,
    @SerialName("isDone") val isDone: Boolean,
)

@Serializable
enum class PhotoStatusDTO {
    @SerialName("NONE") NONE,
    @SerialName("MY_REJECT") MY_REJECT,
    @SerialName("OTHER_REJECT") OTHER_REJECT,
    @SerialName("REQUIRE_SELECT_OTHER_SELECT") REQUIRE_SELECT_OTHER_SELECT,
    @SerialName("REQUIRE_SELECT_OTHER_NOT_SELECT") REQUIRE_SELECT_OTHER_NOT_SELECT,
    @SerialName("WAITING_OTHER_ANSWER") WAITING_OTHER_ANSWER,
    @SerialName("BOTH_AGREE") BOTH_AGREE,
    ;
}

@Serializable
data class MatchResultDTO(
    @SerialName("matchStatus") val matchStatus: MatchStatusTypeDTO,
    @SerialName("otherContact") val otherContact: String,
    @SerialName("shouldAnswer") val shouldAnswer: Boolean,
    @SerialName("isFirstSelect") val isFirstSelect: Boolean,
)

@Serializable
enum class MatchStatusTypeDTO {
    @SerialName("IN_CONVERSATION") IN_CONVERSATION,
    @SerialName("MATCH_FAILED") MATCH_FAILED,
    @SerialName("MATCH_SUCCEEDED") MATCH_SUCCEEDED,
    ;
}