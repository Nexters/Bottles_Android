package com.team.bottles.core.domain.bottle.usecase

import com.team.bottles.core.domain.bottle.model.PingPongDetail
import com.team.bottles.core.domain.bottle.repository.BottleRepository
import javax.inject.Inject

class GetPingPongDetailUseCaseImpl @Inject constructor(
    private val bottleRepository: BottleRepository,
): GetPingPongDetailUseCase {

    override suspend fun invoke(bottleId: Int): PingPongDetail =
        bottleRepository.loadPingPongDetail(bottleId = bottleId)

}

interface GetPingPongDetailUseCase {

    suspend operator fun invoke(bottleId: Int): PingPongDetail

}