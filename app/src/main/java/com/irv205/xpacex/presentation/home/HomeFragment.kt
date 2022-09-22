package com.irv205.xpacex.presentation.home

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.irv205.xpacex.R
import com.irv205.xpacex.databinding.FragmentHomeBinding
import com.irv205.xpacex.domain.models.LaunchesDetails
import com.irv205.xpacex.domain.models.RocketResponse
import com.irv205.xpacex.core.utils.LoadingDialog
import com.irv205.xpacex.core.Failure
import com.irv205.xpacex.core.OnFailure
import com.irv205.xpacex.core.observe
import com.irv205.xpacex.core.utils.parseDate
import com.irv205.xpacex.core.utils.setGlideImage
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

@AndroidEntryPoint
class HomeFragment : Fragment(), OnFailure {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModels<HomeViewModel>()
    val navController by lazy { findNavController() }
    private val adapter by lazy { HomeAdapter(::onClickLaunches) }
    private val loadingDialog: LoadingDialog by lazy { LoadingDialog.newInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.apply {
            observe(homeViewState, ::onViewStateChanged)
            observe(failure, this@HomeFragment::handleFailure)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
    }

    private fun setUpView(){
        binding.rvLaunchesList.adapter = adapter
        adapter.submitList(viewModel.launchesList)
        binding.SwipeList.setOnRefreshListener {
            binding.SwipeList.isRefreshing = false
            viewModel.getListLauncher()
        }
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) binding.icCarouselHome?.setData(viewModel.carousellist)
    }

    private fun onViewStateChanged(viewState: HomeViewState?){
        when(viewState){
            is HomeViewState.Success -> {
                adapter.notifyDataSetChanged()
            }
            is HomeViewState.GetRocket -> {
                setRocketInfo(viewState.rocket)
            }
            is HomeViewState.GetLaunches -> {
                setLaunchesData(viewState.launchesDetails)
            }
            is HomeViewState.EmptyList -> {}
            is HomeViewState.Failure -> {}
            is HomeViewState.ShowProgress -> {
                if (viewState.show) loadingDialog.showDialog(requireActivity().supportFragmentManager)
                else loadingDialog.dismiss()
            }
            else -> {}
        }
    }

    private fun onClickLaunches(launchesDetails: LaunchesDetails){

        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            viewModel.getLauncheById(launchesDetails.id)
        } else {
            navController.navigate(HomeFragmentDirections.actionHomeFragmentToDetailsFragment(launchesDetails.id))
        }
    }

    private fun setLaunchesData(launchesDetails: LaunchesDetails){
        binding.apply {
            icCarouselHome?.visibility = View.GONE
            scrollView?.visibility = View.VISIBLE
            Picasso.get().load(launchesDetails.links.mission_patch.small).into(binding.ivLandPatchHome)
            tvLandNameTitle?.text = launchesDetails.mission_name
            tvDateLandHome?.text = parseDate(launchesDetails.launch_date_utc!!)
            tvLandDescription?.text = launchesDetails.details
        }
        viewModel.getRocketInfo(launchesDetails.rocket)
    }

    private fun setRocketInfo(rocket: RocketResponse){
        val status : String
        binding.apply {
            tvLandHomeNameRocket?.text = rocket.rocket_name
            tvLandHomeModelRocket?.text = rocket.rocket_type
            tvLandHomeStaticRocket?.text = parseDate(rocket.first_flight!!)
            status = if (rocket.active) getString(R.string.success_launches) else getString(R.string.failure_launches)
            tvLandHomeSuccessRocket?.text = status
            for (item in rocket.flickr_images.indices){
                viewModel.carouselRocket = mutableListOf(
                    CarouselItem(imageUrl = rocket.flickr_images[item])
                )
            }
            if (viewModel.carouselRocket.isNotEmpty()) icCarouselRocket?.setData(viewModel.carouselRocket)
            tvLandHomeRocketdDescription?.text = rocket.description
        }
    }

    override fun handleFailure(failure: Failure?) {
        when (failure) {
            is Failure.NetWorkConnection -> Toast.makeText(requireContext(), "FAILURE TEST", Toast.LENGTH_LONG).show()
            is Failure.ServerError -> failure.errorResponse?.message?.let { Toast.makeText(requireContext(), failure.errorResponse.message, Toast.LENGTH_LONG).show() }
            is Failure.Unauthorized -> failure.errorResponse?.message?.let { Toast.makeText(requireContext(), failure.errorResponse.message, Toast.LENGTH_LONG).show() }
            else -> {}
        }
    }
}