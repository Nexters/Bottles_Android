package com.team.bottles.core.domain.bottle.usecase

import com.team.bottles.core.domain.bottle.repository.BottleRepository
import javax.inject.Inject

class SelectPingPongShareKakaoIdUseCaseImpl @Inject constructor(
    private val bottleRepository: BottleRepository,
): SelectPingPongShareKakaoIdUseCase {

    override suspend fun invoke(bottleId: Int, willMatch: Boolean) {
        bottleRepository.selectPingPongShareKakaoId(bottleId = bottleId, willMatch = willMatch)
    }

}

interface SelectPingPongShareKakaoIdUseCase {

    suspend operator fun invoke(bottleId: Int, willMatch: Boolean)

}