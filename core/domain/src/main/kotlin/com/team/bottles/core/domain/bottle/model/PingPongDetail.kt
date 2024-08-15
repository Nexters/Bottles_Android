package com.team.bottles.core.domain.bottle.model

import com.team.bottles.core.domain.profile.model.UserProfile

data class PingPongDetail(
    val isStopped: Boolean,
    val stopUserName: String,
    val deleteAfterDays: Long,
    val userProfile: UserProfile,
    val letters: List<PingPongLetter>,
    val photos: PingPongPhotos,
    val matchResult: PingPongMatchResult,
    val pingPongPhotoStatus: PingPongPhotoStatus,
    val pingPongMatchStatus: PingPongMatchStatus
)