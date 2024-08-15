package com.team.bottles.core.domain.bottle.usecase

import com.team.bottles.core.domain.bottle.repository.BottleRepository
import javax.inject.Inject

class SendPingPongLetterUseCaseImpl @Inject constructor(
    private val bottleRepository: BottleRepository,
) : SendPingPongLetterUseCase {

    override suspend fun invoke(bottleId: Int, letterOrder: Int, answer: String) {
        bottleRepository.sendPingPongLetter(bottleId = bottleId, letterOrder = letterOrder, answer = answer)
    }

}

interface SendPingPongLetterUseCase {

    suspend operator fun invoke(
        bottleId: Int,
        letterOrder: Int,
        answer: String
    )

}