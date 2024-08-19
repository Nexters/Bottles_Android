package com.team.bottles.core.data.mapper

import com.team.bottles.core.domain.bottle.model.PingPongDetail
import com.team.bottles.core.domain.bottle.model.PingPongLetter
import com.team.bottles.core.domain.bottle.model.PingPongMatchResult
import com.team.bottles.core.domain.bottle.model.PingPongMatchStatus
import com.team.bottles.core.domain.bottle.model.PingPongPhotoStatus
import com.team.bottles.core.domain.bottle.model.PingPongPhotos
import com.team.bottles.core.domain.profile.model.UserProfile
import com.team.bottles.core.domain.profile.model.UserProfileSelect
import com.team.bottles.network.dto.bottle.reponse.BottlePingPongResponse
import com.team.bottles.network.dto.bottle.reponse.MatchResultDTO
import com.team.bottles.network.dto.bottle.reponse.MatchStatusTypeDTO
import com.team.bottles.network.dto.bottle.reponse.PhotoDTO
import com.team.bottles.network.dto.bottle.reponse.PhotoStatusDTO
import com.team.bottles.network.dto.bottle.reponse.PingPongLetterDTO
import com.team.bottles.network.dto.bottle.reponse.PingPongUserProfileDTO
import com.team.bottles.network.dto.profile.reponse.QuestionAndAnswerDTO

fun BottlePingPongResponse.toPingPongDetail(): PingPongDetail =
    PingPongDetail(
        isStopped = this.isStopped,
        stopUserName = this.stopUserName?: "",
        deleteAfterDays = this.deleteAfterDays?: 0L,
        userProfile = this.userProfile.toUserProfile(this.introduction?: emptyList()),
        letters = this.letters.map { it.toPingPongLetter() },
        photos = this.photo.toPingPongPhotos(),
        matchResult = this.matchResult.toPingPongMatchResult(),
        pingPongPhotoStatus = this.photo.photoStatus.toPingPongPhotoStatus(),
        pingPongMatchStatus = this.matchResult.matchStatus.toPingPongMatchStatus(),
    )

fun PingPongUserProfileDTO.toUserProfile(introduction: List<QuestionAndAnswerDTO>): UserProfile =
    UserProfile(
        userId = this.userId,
        userName = this.userName,
        age = this.age,
        imageUrl = this.userImageUrl?: "",
        introduction = introduction.map { it.toQuestionAndAnswer() },
        profileSelect = this.profileSelect?.toUserProfileSelect()?: UserProfileSelect()
    )

fun PingPongLetterDTO.toPingPongLetter(): PingPongLetter =
    PingPongLetter(
        canShow = this.canshow,
        isDone = this.isDone,
        myAnswer = this.myAnswer?: "",
        order = this.order,
        question = this.question,
        otherAnswer = this.otherAnswer?: "",
        shouldAnswer = this.shouldAnswer
    )

fun PhotoDTO.toPingPongPhotos(): PingPongPhotos =
    PingPongPhotos(
        myImageUrl = this.myImageUrl?: "",
        otherImageUrl = this.otherImageUrl?: ""
    )

fun MatchResultDTO.toPingPongMatchResult(): PingPongMatchResult =
    PingPongMatchResult(
        otherContact = this.otherContact,
        isFirstSelect = this.isFirstSelect
    )

fun PhotoStatusDTO.toPingPongPhotoStatus(): PingPongPhotoStatus =
    when(this) {
        PhotoStatusDTO.NONE -> PingPongPhotoStatus.NONE
        PhotoStatusDTO.MY_REJECT -> PingPongPhotoStatus.MY_REJECT
        PhotoStatusDTO.OTHER_REJECT -> PingPongPhotoStatus.OTHER_REJECT
        PhotoStatusDTO.REQUIRE_SELECT_OTHER_SELECT -> PingPongPhotoStatus.REQUIRE_SELECT
        PhotoStatusDTO.REQUIRE_SELECT_OTHER_NOT_SELECT -> PingPongPhotoStatus.REQUIRE_SELECT
        PhotoStatusDTO.WAITING_OTHER_ANSWER -> PingPongPhotoStatus.WAITING_OTHER_ANSWER
        PhotoStatusDTO.BOTH_AGREE -> PingPongPhotoStatus.BOTH_AGREE
    }

fun MatchStatusTypeDTO.toPingPongMatchStatus(): PingPongMatchStatus =
    when(this) {
        MatchStatusTypeDTO.NONE -> PingPongMatchStatus.NONE
        MatchStatusTypeDTO.MATCH_SUCCEEDED -> PingPongMatchStatus.MATCH_SUCCEEDED
        MatchStatusTypeDTO.MATCH_FAILED -> PingPongMatchStatus.MATCH_FAILED
        MatchStatusTypeDTO.REQUIRE_SELECT -> PingPongMatchStatus.REQUIRE_SELECT
        MatchStatusTypeDTO.WAITING_OTHER_ANSWER -> PingPongMatchStatus.WAITING_OTHER_ANSWER
    }