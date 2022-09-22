package com.irv205.xpacex.presentation.details

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.irv205.xpacex.R
import com.irv205.xpacex.databinding.FragmentDetailsBinding
import com.irv205.xpacex.domain.models.LaunchesDetails
import com.irv205.xpacex.domain.models.RocketResponse
import com.irv205.xpacex.core.utils.LoadingDialog
import com.irv205.xpacex.core.observe
import com.irv205.xpacex.core.utils.parseDate
import com.irv205.xpacex.core.utils.setGlideImage
import com.irv205.xpacex.core.utils.setGlideImageBG
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private val viewModel by viewModels<DetailsViewModel>()
    private val args: DetailsFragmentArgs by navArgs()
    private val loadingDialog: LoadingDialog by lazy { LoadingDialog.newInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.apply {
            observe(detailsViewState, ::onViewStateChanged)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        args.launcheID?.let {
            viewModel.getLauncheById(it)
        }
        setUpView()
        binding.icCarouselDetails.setData(viewModel.carousellist)
    }

    private fun setUpView(){

    }

    private fun onViewStateChanged(viewState: DetailsViewState?){
        when(viewState) {
            is DetailsViewState.GetLaunches ->{
                setData(viewState.launchesDetails)
            }
            is DetailsViewState.GetRocket -> {
                setDataRocket(viewState.rocketResponse)
            }
            is DetailsViewState.ShowProgress -> {
                if (viewState.show) loadingDialog.showDialog(requireActivity().supportFragmentManager)
                else loadingDialog.dismiss()
            }
            else -> {}
        }
    }

    private fun setData(launchesDetails: LaunchesDetails){
        Picasso.get().load(launchesDetails.links.mission_patch.small).into(binding.ivLogoDetails)
        binding.ivytDetails.setGlideImageBG(R.drawable.yt)
        binding.tvTitleDetails.text = launchesDetails.mission_name
        binding.tvDateDetails.text = parseDate(launchesDetails.launch_date_utc!!)
        binding.tvDescriptionDetails.text = launchesDetails.details
        viewModel.getRocketInfo(launchesDetails.rocket)
    }

    private fun setDataRocket(rocketResponse: RocketResponse) {
        binding.apply {
            tvNameRocketDetails.text = rocketResponse.rocket_name
            tvTypeRocketDetails.text = rocketResponse.rocket_type
            tvStaticFireRocketDetails.text = parseDate(rocketResponse.first_flight!!)
            tvCountryRocketDetails.text = rocketResponse.country
            if (rocketResponse.active) ivActiveRocketDetails.setGlideImage(R.drawable.check)
            else ivActiveRocketDetails.setGlideImage(R.drawable.close)
            tvCompanyRocketDetails.text = rocketResponse.company
            tvDescriptionRocketDetails.text = rocketResponse.description
            if (viewModel.carousellist.isNotEmpty()) icCarouselRocketlDetails.setData(viewModel.carouselRocket)
        }
    }
}