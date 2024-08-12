package com.team.bottles.core.ui.model

data class Bottle(
    val id: Long = 0,
    val imageUrl: String = "",
    val name: String = "",
    val age: Int = 0,
    val mbti: String = "",
    val personality: List<String> = emptyList(),
    val isRead: Boolean = true,
    val remainingTime: String? = null,
) {
    companion object {
        fun exampleBottleBox(): List<Bottle> =
            (1L..6L).map {
                Bottle(
                    id = it,
                    imageUrl = "https://avatars.githubusercontent.com/u/54674781?v=4",
                    name = "냥냥이",
                    age = 15,
                    remainingTime = null,
                    mbti = "INTP",
                    personality = listOf("적극적인", "열적정인", "예의바른"),
                    isRead = false
                )
            }
    }
}
