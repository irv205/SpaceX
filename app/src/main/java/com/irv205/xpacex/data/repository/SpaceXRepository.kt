package com.irv205.xpacex.data.repository

import com.irv205.xpacex.domain.models.LaunchesDetails
import com.irv205.xpacex.domain.models.RocketResponse
import com.irv205.xpacex.core.Either
import com.irv205.xpacex.core.Failure

interface SpaceXRepository {
    fun getLaunchesList(): Either<Failure, List<LaunchesDetails>>
    fun getLauncheById(launche_id: String): Either<Failure, LaunchesDetails>
    fun getRocketInfo(rocket_id: String): Either<Failure, RocketResponse>
}