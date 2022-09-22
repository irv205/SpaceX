package com.irv205.xpacex.data.repository

import androidx.lifecycle.viewmodel.CreationExtras
import com.irv205.xpacex.core.ApiRequest
import com.irv205.xpacex.core.Either
import com.irv205.xpacex.core.Failure
import com.irv205.xpacex.data.ApiSpacesX
import com.irv205.xpacex.domain.models.LaunchesDetails
import com.irv205.xpacex.domain.models.RocketResponse
import com.irv205.xpacex.core.utils.NetworkHandler
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
data class SpaceXRepositoryImplement @Inject constructor (
    private val networkHandler: NetworkHandler,
    private val apiSpaceX: ApiSpacesX
): SpaceXRepository, ApiRequest {
    override fun getLaunchesList(): Either<Failure, List<LaunchesDetails>> {
        return when (networkHandler.isConnected) {
            true -> {
                makeRequest(
                    apiSpaceX.getLaunchesList(),
                    { space ->
                        space
                    },
                    emptyList())
            } else -> Either.Left(Failure.NetWorkConnection)
        }
    }

    override fun getLauncheById(launche_id: String): Either<Failure, LaunchesDetails> {
        return when(networkHandler.isConnected) {
            true -> {
                makeRequest(
                    apiSpaceX.getLauncheByID(launche_id),
                    {   launche ->
                        launche
                    },
                    LaunchesDetails.emptyLaunche()
                )
            } else -> Either.Left(Failure.NetWorkConnection)
        }
    }

    override fun getRocketInfo(rocket_id: String): Either<Failure, RocketResponse> {
        return when (networkHandler.isConnected) {
            true -> {
                makeRequest(
                    apiSpaceX.getRocketInfo(rocket_id),
                    { rocket ->
                        rocket
                    },
                    RocketResponse.empty()
                )
            } else -> Either.Left(Failure.NetWorkConnection)
        }
    }

}