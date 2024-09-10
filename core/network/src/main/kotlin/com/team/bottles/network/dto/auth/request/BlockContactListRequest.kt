package com.team.bottles.network.dto.auth.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BlockContactListRequest(
    @SerialName("blockContacts") val blockContacts: List<String>
)