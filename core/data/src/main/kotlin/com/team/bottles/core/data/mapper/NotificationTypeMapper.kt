package com.team.bottles.core.data.mapper

import com.team.bottles.core.domain.user.model.NotificationType
import com.team.bottles.network.dto.user.response.AlimyType

fun NotificationType.toAlimyType(): AlimyType =
    when (this) {
        NotificationType.PING_PONG -> AlimyType.PING_PONG
        NotificationType.MARKETING -> AlimyType.MARKETING
        NotificationType.DAILY_RANDOM -> AlimyType.DAILY_RANDOM
        NotificationType.RECEIVE_LIKE -> AlimyType.RECEIVE_LIKE
    }