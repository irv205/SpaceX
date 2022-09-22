package com.irv205.xpacex.presentation.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.irv205.xpacex.domain.models.LaunchesDetails
import com.irv205.xpacex.domain.models.RocketResponse
import com.irv205.xpacex.domain.useCase.GetLauncheByIdUC
import com.irv205.xpacex.domain.useCase.GetRocketInfoUC
import com.irv205.xpacex.presentation.home.HomeViewState
import com.irv205.xpacex.core.Failure
import dagger.hilt.android.lifecycle.HiltViewModel
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getLauncheByIdUC: GetLauncheByIdUC,
    private val getRocketInfoUC: GetRocketInfoUC
): ViewModel() {

    private val _detailsViewState = MutableLiveData<DetailsViewState>()
    val detailsViewState : LiveData<DetailsViewState> get() = _detailsViewState
    val carousellist = mutableListOf(
        CarouselItem(imageUrl = "https://live.staticflickr.com/65535/49927519643_b43c6d4c44_o.jpg"),
        CarouselItem(imageUrl = "https://live.staticflickr.com/65535/49927519588_8a39a3994f_o.jpg"),
        CarouselItem(imageUrl = "https://live.staticflickr.com/65535/49928343022_6fb33cbd9c_o.jpg"),
        CarouselItem(imageUrl = "https://live.staticflickr.com/65535/49934168858_cacb00d790_o.jpg"),
        CarouselItem(imageUrl = "https://live.staticflickr.com/65535/49934682271_fd6a31becc_o.jpg"),
    )
    lateinit var launchesDetails: LaunchesDetails
    lateinit var rocket: RocketResponse
    var carouselRocket = mutableListOf<CarouselItem>()

    fun getLauncheById(id: String){
        _detailsViewState.value = DetailsViewState.ShowProgress(true)
        getLauncheByIdUC(id) {
            _detailsViewState.value = DetailsViewState.ShowProgress(false)
            it.fold(::failureResponse, ::successResponseLaunches)
        }
    }

    fun getRocketInfo(rocket_id: String){
        _detailsViewState.value = DetailsViewState.ShowProgress(true)
        getRocketInfoUC(rocket_id) {
            _detailsViewState.value = DetailsViewState.ShowProgress(false)
            it.fold(::failureResponse, ::rocketResponse)
        }
    }

    fun failureResponse(failure: Failure){
        _detailsViewState.value = DetailsViewState.Failure(failure.toString())
    }

    fun successResponseLaunches(response: LaunchesDetails){
        launchesDetails = response
        _detailsViewState.value = DetailsViewState.GetLaunches(response)
    }

    fun rocketResponse(response: RocketResponse){
        rocket = response
        for (item in rocket.flickr_images.indices){
            carouselRocket = mutableListOf(
                CarouselItem(imageUrl = rocket.flickr_images[item])
            )
        }
        _detailsViewState.value = DetailsViewState.GetRocket(rocket)
    }

}