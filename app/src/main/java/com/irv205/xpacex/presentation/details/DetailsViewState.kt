package com.irv205.xpacex.presentation.details

import com.irv205.xpacex.domain.models.LaunchesDetails
import com.irv205.xpacex.domain.models.RocketResponse

sealed class DetailsViewState {
    object Success: DetailsViewState()
    class GetLaunches(val launchesDetails: LaunchesDetails): DetailsViewState()
    class GetRocket(val rocketResponse: RocketResponse): DetailsViewState()
    class Failure(val message: String): DetailsViewState()
    class ShowProgress(val show: Boolean) : DetailsViewState()
}