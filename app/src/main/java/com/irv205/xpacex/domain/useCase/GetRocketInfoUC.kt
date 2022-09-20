package com.irv205.xpacex.domain.useCase

import com.irv205.xpacex.data.repository.SpaceXRepository
import com.irv205.xpacex.domain.models.RocketResponse
import com.irv205.xpacex.utils.UseCase
import com.irv205.xpacex.utils.core.Either
import com.irv205.xpacex.utils.core.Failure
import javax.inject.Inject

class GetRocketInfoUC @Inject constructor(
    private val spaceXRepository: SpaceXRepository
): UseCase<RocketResponse, String>() {
    override suspend fun run(params: String): Either<Failure, RocketResponse> {
        return spaceXRepository.getRocketInfo(params)
    }
}