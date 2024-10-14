package com.team.bottles.core.ui.model

data class Bottle(
    val id: Long = 0,
    val imageUrl: String = "",
    val name: String = "",
    val age: Int = 0,
    val mbti: String = "",
    val personality: List<String> = emptyList(),
    val isRead: Boolean = true,
    val lastActivatedAt: String = "",
    val lastStatus: String = ""
) {
    companion object {
        fun exampleBottleList(): List<Bottle> =
            (1L..6L).map {
                Bottle(
                    id = it,
                    imageUrl = "https://avatars.githubusercontent.com/u/54674781?v=4",
                    name = "냥냥이",
                    age = 15,
                    lastActivatedAt = "00분 전",
                    mbti = "INTP",
                    personality = listOf("적극적인", "열적정인", "예의바른"),
                    isRead = false,
                    lastStatus = "연락처를 공유했어요"
                )
            }
    }
}
