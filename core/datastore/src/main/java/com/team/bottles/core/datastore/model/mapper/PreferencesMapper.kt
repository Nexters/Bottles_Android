package com.team.bottles.core.datastore.model.mapper

import com.team.bottles.core.datastore.Interest
import com.team.bottles.core.datastore.Introduction
import com.team.bottles.core.datastore.ProfileSelect
import com.team.bottles.core.datastore.Region
import com.team.bottles.core.datastore.model.LocalInterests
import com.team.bottles.core.datastore.model.LocalIntroduction
import com.team.bottles.core.datastore.model.LocalProfileSelect
import com.team.bottles.core.datastore.model.LocalRegion

fun List<Introduction>.toLocalData() =
    this.map { introduction ->
        LocalIntroduction(
            question = introduction.question,
            answer = introduction.answer
        )
    }

fun ProfileSelect.toLocalData() =
    LocalProfileSelect(
        mbti = this.mbti,
        keywords = this.keywordList,
        interests = this.interest.toLocalData(),
        smoking = this.smoking,
        alcohol = this.alcohol,
        religion = this.religion,
        region = this.region.toLocalData()
    )

fun Interest.toLocalData() =
    LocalInterests(
        culture = this.cultureList,
        sports = this.sportsList,
        entertainment = this.entertainmentList,
        etc = this.etcList
    )

fun Region.toLocalData() =
    LocalRegion(
        city = this.city,
        state = this.state
    )