package com.irv205.xpacex.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.irv205.xpacex.domain.models.LaunchesDetails
import com.irv205.xpacex.domain.models.RocketResponse
import com.irv205.xpacex.domain.useCase.GetLauncheByIdUC
import com.irv205.xpacex.domain.useCase.GetLaunchesListUC
import com.irv205.xpacex.domain.useCase.GetRocketInfoUC
import com.irv205.xpacex.presentation.details.DetailsViewState
import com.irv205.xpacex.core.utils.UseCase
import com.irv205.xpacex.core.Failure
import dagger.hilt.android.lifecycle.HiltViewModel
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getLaunchesListUC: GetLaunchesListUC,
    private val getLauncheByIdUC: GetLauncheByIdUC,
    private val getRocketInfoUC: GetRocketInfoUC
): ViewModel() {

    private val _homeViewState = MutableLiveData<HomeViewState>()
    val homeViewState : LiveData<HomeViewState> get() = _homeViewState
    var failure: MutableLiveData<Failure> = MutableLiveData()
    val launchesList = mutableListOf<LaunchesDetails>()
    var rocket : RocketResponse = RocketResponse.empty()
    val carousellist = mutableListOf(
        CarouselItem(imageUrl = "https://live.staticflickr.com/65535/49927519643_b43c6d4c44_o.jpg"),
        CarouselItem(imageUrl = "https://live.staticflickr.com/65535/49927519588_8a39a3994f_o.jpg"),
        CarouselItem(imageUrl = "https://live.staticflickr.com/65535/49928343022_6fb33cbd9c_o.jpg"),
        CarouselItem(imageUrl = "https://live.staticflickr.com/65535/49934168858_cacb00d790_o.jpg"),
        CarouselItem(imageUrl = "https://live.staticflickr.com/65535/49934682271_fd6a31becc_o.jpg"),
    )
    lateinit var launchesDetails: LaunchesDetails
    var carouselRocket = mutableListOf<CarouselItem>()

    init {
        getListLauncher()
    }

    fun getListLauncher(){
        launchesList.clear()
        _homeViewState.value = HomeViewState.ShowProgress(true)
        getLaunchesListUC(UseCase.None()) {
            _homeViewState.value = HomeViewState.ShowProgress(false)
            it.fold(::failureResponse, ::successResponse)
        }
    }

    fun getLauncheById(id: String){
        _homeViewState.value = HomeViewState.ShowProgress(true)
        getLauncheByIdUC(id) {
            _homeViewState.value = HomeViewState.ShowProgress(false)
            it.fold(::failureResponse, ::successResponseLaunches)
        }
    }

    fun successResponseLaunches(response: LaunchesDetails){
        launchesDetails = response
        _homeViewState.value = HomeViewState.GetLaunches(response)
    }

    private fun successResponse(response: List<LaunchesDetails>){
        if (response.isNotEmpty()) {
            launchesList.addAll(response)
            _homeViewState.value = HomeViewState.Success
        } else _homeViewState.value = HomeViewState.EmptyList
    }

    fun getRocketInfo(rocket_id: String){
        _homeViewState.value = HomeViewState.ShowProgress(true)
        getRocketInfoUC(rocket_id) {
            _homeViewState.value = HomeViewState.ShowProgress(false)
            it.fold(::failureResponse, ::rocketResponse)
        }
    }

    fun rocketResponse(response: RocketResponse){
        rocket = response
        _homeViewState.value = HomeViewState.GetRocket(response)
    }

    protected fun failureResponse(failure: Failure){
        this.failure.value = failure
        _homeViewState.value = HomeViewState.Failure("")
    }
}