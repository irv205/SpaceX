package com.irv205.xpacex.domain.useCase

import com.irv205.xpacex.core.Either
import com.irv205.xpacex.core.Failure
import com.irv205.xpacex.data.repository.SpaceXRepository
import com.irv205.xpacex.domain.models.LaunchesDetails
import com.irv205.xpacex.core.utils.UseCase
import javax.inject.Inject

class GetLaunchesListUC @Inject constructor(
    private val spaceXRepository: SpaceXRepository,
): UseCase<List<LaunchesDetails>, UseCase.None>() {
    override suspend fun run(params: None): Either<Failure, List<LaunchesDetails>> {
        return spaceXRepository.getLaunchesList()
    }
}