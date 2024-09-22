package com.team.bottles.core.data.mapper

import com.team.bottles.core.domain.profile.model.Interest
import com.team.bottles.core.domain.profile.model.QuestionAndAnswer
import com.team.bottles.core.domain.profile.model.Region
import com.team.bottles.core.domain.profile.model.UserProfile
import com.team.bottles.core.domain.profile.model.UserProfileSelect
import com.team.bottles.network.dto.profile.reponse.InterestDto
import com.team.bottles.network.dto.profile.reponse.QuestionAndAnswerDTO
import com.team.bottles.network.dto.profile.reponse.RegionDto
import com.team.bottles.network.dto.profile.reponse.UserProfileResponse
import com.team.bottles.network.dto.profile.reponse.UserProfileSelectDTO

fun UserProfileResponse.toUserProfile(): UserProfile =
    UserProfile(
        userName = this.userName,
        age = this.age,
        blockedUserCount = this.blockedUserCount,
        imageUrl = this.imageUrl?: "",
        introduction = this.introduction.map { dto -> dto.toQuestionAndAnswer() },
        profileSelect = this.profileSelect?.toUserProfileSelect()?: UserProfileSelect()
    )

fun QuestionAndAnswerDTO.toQuestionAndAnswer(): QuestionAndAnswer =
    QuestionAndAnswer(
        question = this.question,
        answer = this.answer
    )

fun UserProfileSelectDTO.toUserProfileSelect(): UserProfileSelect =
    UserProfileSelect(
        mbti = this.mbti,
        keyword = this.keyword,
        interest = this.interest.toInterest(),
        job = this.job,
        height = this.height,
        smoking = this.smoking,
        alcohol = this.alcohol,
        religion = this.religion,
        region = this.region.toRegion(),
    )

fun InterestDto.toInterest(): Interest =
    Interest(
        culture = this.culture,
        sports = this.sports,
        entertainment = this.entertainment,
        etc = this.etc
    )

fun RegionDto.toRegion(): Region =
    Region(
        city = this.city,
        state = this.state
    )