package com.team.bottles.core.domain.bottle.usecase

import com.team.bottles.core.domain.bottle.repository.BottleRepository
import javax.inject.Inject

class StopPingPongUseCaseImpl @Inject constructor(
    private val bottleRepository: BottleRepository,
): StopPingPongUseCase {

    override suspend fun invoke(bottleId: Int) {
        bottleRepository.stopPingPong(bottleId = bottleId)
    }

}

interface StopPingPongUseCase {

    suspend operator fun invoke(bottleId: Int)

}