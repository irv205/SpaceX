package com.irv205.xpacex.presentation.home

import com.irv205.xpacex.domain.models.LaunchesDetails
import com.irv205.xpacex.domain.models.RocketResponse

sealed class HomeViewState {
    object Success: HomeViewState()
    object EmptyList: HomeViewState()
    class GetLaunches(val launchesDetails: LaunchesDetails): HomeViewState()
    class GetRocket(val rocket: RocketResponse): HomeViewState()
    class Failure(val message: String): HomeViewState()
    class ShowProgress(val show: Boolean) : HomeViewState()
}