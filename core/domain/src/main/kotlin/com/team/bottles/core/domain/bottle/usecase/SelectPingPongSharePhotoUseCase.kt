package com.team.bottles.core.domain.bottle.usecase

import com.team.bottles.core.domain.bottle.repository.BottleRepository
import javax.inject.Inject

class SelectPingPongSharePhotoUseCaseImpl @Inject constructor(
    private val bottleRepository: BottleRepository,
): SelectPingPongSharePhotoUseCase {

    override suspend fun invoke(bottleId: Int, willShare: Boolean) {
        bottleRepository.selectPingPongSharePhoto(bottleId = bottleId, willShare = willShare)
    }

}

interface SelectPingPongSharePhotoUseCase {

    suspend operator fun invoke(bottleId: Int, willShare: Boolean)

}