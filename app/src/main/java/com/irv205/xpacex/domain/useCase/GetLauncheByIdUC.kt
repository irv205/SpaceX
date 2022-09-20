package com.irv205.xpacex.domain.useCase

import com.irv205.xpacex.data.repository.SpaceXRepository
import com.irv205.xpacex.domain.models.LaunchesDetails
import com.irv205.xpacex.utils.UseCase
import com.irv205.xpacex.utils.core.Either
import com.irv205.xpacex.utils.core.Failure
import javax.inject.Inject

class GetLauncheByIdUC @Inject constructor(
    private val spaceXRepository: SpaceXRepository
): UseCase<LaunchesDetails, String>() {
    override suspend fun run(params: String): Either<Failure, LaunchesDetails> {
        return spaceXRepository.getLauncheById(params)
    }
}