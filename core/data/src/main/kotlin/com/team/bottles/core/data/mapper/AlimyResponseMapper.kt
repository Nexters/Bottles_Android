package com.team.bottles.core.data.mapper

import com.team.bottles.core.domain.user.model.Notification
import com.team.bottles.core.domain.user.model.NotificationType
import com.team.bottles.network.dto.user.response.AlimyResponse
import com.team.bottles.network.dto.user.response.AlimyType

fun AlimyResponse.toNotification(): Notification =
    Notification(
        notificationType = this.alimyType.toNotificationType(),
        enabled = this.enabled
    )

fun AlimyType.toNotificationType(): NotificationType =
    when (this) {
        AlimyType.MARKETING -> NotificationType.MARKETING
        AlimyType.DAILY_RANDOM -> NotificationType.DAILY_RANDOM
        AlimyType.PING_PONG -> NotificationType.PING_PONG
        AlimyType.RECEIVE_LIKE -> NotificationType.RECEIVE_LIKE
    }