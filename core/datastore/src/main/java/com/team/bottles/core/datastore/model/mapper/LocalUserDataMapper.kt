package com.team.bottles.core.datastore.model.mapper

import com.team.bottles.core.datastore.Interest
import com.team.bottles.core.datastore.Introduction
import com.team.bottles.core.datastore.ProfileSelect
import com.team.bottles.core.datastore.Region
import com.team.bottles.core.datastore.model.LocalInterests
import com.team.bottles.core.datastore.model.LocalIntroduction
import com.team.bottles.core.datastore.model.LocalProfileSelect
import com.team.bottles.core.datastore.model.LocalRegion

fun List<LocalIntroduction>.toDataStore() =
    this.map { localIntroduction ->
        Introduction.newBuilder()
            .setQuestion(localIntroduction.question)
            .setAnswer(localIntroduction.answer)
            .build()
    }

fun LocalProfileSelect.toDataStore(): ProfileSelect.Builder =
    ProfileSelect.newBuilder()
        .setMbti(this.mbti)
        .addAllKeyword(this.keywords)
        .setInterest(this.interests.toDataStore())
        .setJob(this.job)
        .setSmoking(this.smoking)
        .setAlcohol(this.alcohol)
        .setReligion(this.religion)
        .setRegion(this.region.toDataStore())

fun LocalInterests.toDataStore(): Interest.Builder =
    Interest.newBuilder()
        .addAllCulture(this.culture)
        .addAllSports(this.sports)
        .addAllEntertainment(this.entertainment)
        .addAllEtc(this.etc)

fun LocalRegion.toDataStore(): Region.Builder =
    Region.newBuilder()
        .setCity(this.city)
        .setState(this.state)