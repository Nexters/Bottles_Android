package com.team.bottles.core.ui.model

data class UserKeyPoint(
    val subtitle: String,
    val properties: List<String>
) {
    companion object {
        fun exampleUerKeyPoints(): List<UserKeyPoint> = listOf(
            UserKeyPoint(
                subtitle = "기본 정보",
                properties = listOf("직장인", "MBTI", "도시이름", "키", "흡연 안해요", "술을 즐겨요")
            ),
            UserKeyPoint(
                subtitle = "나의 성격은",
                properties = listOf("적극적인", "열정적인", "예의바른", "자유로운", "쿨한")
            ),
            UserKeyPoint(
                subtitle = "내가 푹 빠진 취미는",
                properties = listOf("코인노래방", "헬스", "드라이브", "만화 · 웹툰 정주행", "자전거")
            ),
        )

        fun introduction(
            keyWords: List<String>,
            personality: List<String>,
            hobbies: List<String>
        ): List<UserKeyPoint> = listOf(
            UserKeyPoint(
                subtitle = "내 키워드를 참고해보세요",
                properties = keyWords
            ),
            UserKeyPoint(
                subtitle = "나의 성격은",
                properties = personality
            ),
            UserKeyPoint(
                subtitle = "내가 푹 빠진 취미는",
                properties = hobbies
            ),
        )
    }
}