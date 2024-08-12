package com.team.bottles.core.domain.profile.model

data class UserProfile(
    val userName: String = "",
    val age: Int = 0,
    val imageUrl: String = "",
    val introduction: List<QuestionAndAnswer> = emptyList(),
    val profileSelect: UserProfileSelect = UserProfileSelect(),
) {
    companion object {
        fun sampleUserProfile(): UserProfile = UserProfile(
            userName = "냥냥이",
            age = 15,
            imageUrl = "",
            introduction = QuestionAndAnswer.sampleList(),
            profileSelect = UserProfileSelect.sample()
        )
    }
}

data class UserProfileSelect(
    val mbti: String = "",
    val keyword: List<String> = emptyList(),
    val interest: Interest = Interest(),
    val job: String = "",
    val height: Int = 0,
    val smoking: String = "",
    val alcohol: String = "",
    val religion: String = "",
    val region: Region = Region(),
) {
    companion object {
        fun sample(): UserProfileSelect = UserProfileSelect(
            mbti = "ENTJ",
            keyword = listOf("적극적인", "열정적인", "예의바른", "자유로운", "쿨한"),
            interest = Interest.sample(),
            job = "직장인",
            height = 170,
            smoking = "피우지 않아요",
            alcohol = "술은 잘 안해요",
            religion = "무교",
            region = Region.sample()
        )
    }
}

data class Interest(
    val culture: List<String> = emptyList(),
    val sports: List<String> = emptyList(),
    val entertainment: List<String> = emptyList(),
    val etc: List<String> = emptyList(),
) {
    companion object {
        fun sample(): Interest = Interest(
            culture = listOf("코인노래방"),
            sports = listOf("헬스"),
            entertainment = listOf("만화 · 웹툰 정주행"),
            etc = listOf("드라이브"),
        )
    }
}

data class Region(
    val city: String = "",
    val state: String = ""
) {
    companion object {
        fun sample(): Region = Region(
            city = "서울특별시",
            state = "송파구"
        )
    }
}