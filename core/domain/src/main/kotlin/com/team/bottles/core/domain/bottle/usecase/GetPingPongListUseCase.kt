package com.team.bottles.core.domain.bottle.usecase

import com.team.bottles.core.domain.bottle.model.PingPongBottle
import com.team.bottles.core.domain.bottle.repository.BottleRepository
import javax.inject.Inject

class GetPingPongListUseCaseImpl @Inject constructor(
    private val bottleRepository: BottleRepository,
): GetPingPongListUseCase {

    override suspend fun invoke(): List<PingPongBottle> =
        bottleRepository.loadPingPongList()

}

interface GetPingPongListUseCase {

    suspend operator fun invoke(): List<PingPongBottle>

}