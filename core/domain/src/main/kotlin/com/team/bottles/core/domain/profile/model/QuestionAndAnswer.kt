package com.team.bottles.core.domain.profile.model

data class QuestionAndAnswer(
    val answer: String = "",
    val question: String = ""
) {
    companion object {
        fun sampleList(): List<QuestionAndAnswer> = listOf(
            QuestionAndAnswer(
                question = "나중에 추가될 데이터",
                answer = "안녕하세요. 자기소개 입니다."
            )
        )
    }
}
