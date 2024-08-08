package com.team.bottles.core.domain.bottle.usecase

import com.team.bottles.core.domain.bottle.model.PingPongResult
import com.team.bottles.core.domain.bottle.repository.BottleRepository
import javax.inject.Inject

class GetPingPongListUseCaseImpl @Inject constructor(
    private val bottleRepository: BottleRepository,
): GetPingPongListUseCase {

    override suspend fun invoke(): PingPongResult =
        bottleRepository.loadPingPongList()

}

interface GetPingPongListUseCase {

    suspend operator fun invoke(): PingPongResult

}