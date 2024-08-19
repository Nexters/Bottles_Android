package com.team.bottles.core.data.mapper

import com.team.bottles.core.domain.profile.model.UserProfileStatus
import com.team.bottles.network.dto.profile.reponse.UserProfileStatusDTO
import com.team.bottles.network.dto.profile.reponse.UserProfileStatusResponse

fun UserProfileStatusResponse.toUserProfileStatus(): UserProfileStatus =
    when(this.userProfileStatus) {
        UserProfileStatusDTO.EMPTY -> UserProfileStatus.EMPTY
        UserProfileStatusDTO.ONLY_PROFILE_CREATED -> UserProfileStatus.ONLY_PROFILE_CREATED
        UserProfileStatusDTO.PHOTO_DONE -> UserProfileStatus.PHOTO_DONE
        UserProfileStatusDTO.INTRODUCE_DONE -> UserProfileStatus.INTRODUCE_DONE
    }