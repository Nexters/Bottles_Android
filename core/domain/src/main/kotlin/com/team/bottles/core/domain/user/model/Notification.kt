package com.team.bottles.core.domain.user.model

data class Notification(
    val notificationType: NotificationType,
    val enabled: Boolean
)

enum class NotificationType {
    DAILY_RANDOM,
    RECEIVE_LIKE,
    PING_PONG,
    MARKETING,
    ;
}