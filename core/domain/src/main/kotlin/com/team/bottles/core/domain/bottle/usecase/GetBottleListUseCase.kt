package com.team.bottles.core.domain.bottle.usecase

import com.team.bottles.core.domain.bottle.model.ArrivedBottle
import com.team.bottles.core.domain.bottle.repository.BottleRepository
import javax.inject.Inject

class GetBottleListUseCaseImpl @Inject constructor(
    private val bottleRepository: BottleRepository,
): GetBottleListUseCase {

    override suspend fun invoke(): ArrivedBottle =
        bottleRepository.loadBottleList()

}

interface GetBottleListUseCase {

    suspend operator fun invoke(): ArrivedBottle

}