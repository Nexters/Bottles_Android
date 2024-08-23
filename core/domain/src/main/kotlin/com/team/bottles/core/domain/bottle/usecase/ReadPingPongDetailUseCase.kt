package com.team.bottles.core.domain.bottle.usecase

import com.team.bottles.core.domain.bottle.repository.BottleRepository
import javax.inject.Inject

class ReadPingPongDetailUseCaseImpl @Inject constructor(
    private val bottleRepository: BottleRepository,
): ReadPingPongDetailUseCase {

    override suspend fun invoke(bottleId: Int) {
        bottleRepository.updatePingPongReadStatus(bottleId = bottleId)
    }

}

interface ReadPingPongDetailUseCase {

    suspend operator fun invoke(bottleId: Int)

}