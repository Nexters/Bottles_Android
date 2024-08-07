package com.team.bottles.core.domain.profile.model

data class UserProfile(
    val userName: String,
    val age: Int,
    val imageUrl: String,
    val introduction: List<QuestionAndAnswer>,
    val profileSelect: UserProfileSelect,
)

data class UserProfileSelect(
    val mbti: String,
    val keyword: List<String>,
    val interest: Interest,
    val job: String,
    val height: Int,
    val smoking: String,
    val alcohol: String,
    val religion: String,
    val region: Region,
) {
    companion object {
        fun empty(): UserProfileSelect = UserProfileSelect(
            mbti = "",
            keyword = emptyList(),
            interest = Interest.empty(),
            job = "",
            height = 0,
            smoking = "",
            alcohol = "",
            religion = "",
            region = Region.empty()
        )

    }
}

data class Interest(
    val culture: List<String>,
    val sports: List<String>,
    val entertainment: List<String>,
    val etc: List<String>,
) {
    companion object {
        fun empty(): Interest = Interest(
            culture = emptyList(),
            sports = emptyList(),
            entertainment = emptyList(),
            etc = emptyList()
        )
    }
}

data class Region(
    val city: String,
    val state: String
) {
    companion object {
        fun empty(): Region = Region(
            city = "",
            state = ""
        )
    }
}