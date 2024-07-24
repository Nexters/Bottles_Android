package com.team.bottles.core.datastore.model

data class LocalUserData(
    val name: String = "",
    val age: Int = 0,
    val introductions: List<LocalIntroduction> = emptyList(),
    val profileSelect: LocalProfileSelect = LocalProfileSelect()
)

data class LocalIntroduction(
    val question: String = "",
    val answer: String = ""
)

data class LocalProfileSelect(
    val mbti: String = "",
    val keywords: List<String> = emptyList(),
    val interests: LocalInterests = LocalInterests(),
    val job: String = "",
    val smoking: String = "",
    val alcohol: String = "",
    val religion: String = "",
    val region: LocalRegion = LocalRegion()
)

data class LocalInterests(
    val culture: List<String> = emptyList(),
    val sports: List<String> = emptyList(),
    val entertainment: List<String> = emptyList(),
    val etc: List<String> = emptyList(),
)

data class LocalRegion(
    val city: String = "",
    val state: String = ""
)