package com.irv205.xpacex.presentation.home

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.irv205.xpacex.R
import com.irv205.xpacex.databinding.ItemListLaunchesBinding
import com.irv205.xpacex.domain.models.LaunchesDetails
import com.irv205.xpacex.utils.*
import com.squareup.picasso.Picasso

class HomeAdapter(private val callback: ((LaunchesDetails) -> Unit)? = null) :
    ListAdapter<LaunchesDetails, HomeAdapter.ViewHolder>(LaunchesDiffCallback) {

    var index: String = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemListLaunchesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class ViewHolder(private val binding: ItemListLaunchesBinding)
        : RecyclerView.ViewHolder(binding.root) {
            fun bind(item: LaunchesDetails){
                binding.apply {
                    if (item.links.flickr.original.isNullOrEmpty()) ivBackgroundItem.setGlideImageBG(R.drawable.background)
                    else Picasso.get().load(item.links.flickr.original[0]).fit().into(ivBackgroundItem) //ivBackgroundItem.setGlideImageBG(item.links.flickr.original[0])
                    tvNumberItem.text = "# ${item.flight_number}"
                    tvDateItem.text = parseDate(item.launch_date_utc!!)
                    tvNameItem.text = item.mission_name
                    if (item.launch_success) ivStatusItem.setGlideImage(R.drawable.check)
                    else ivStatusItem.setGlideImage(R.drawable.close)
                    Picasso.get().load(item.links.mission_patch.small).into(ivItem)
                    root.setOnClickListener {
                        callback?.let { it(item) }
                    }
                }
            }
    }
}

object LaunchesDiffCallback : DiffUtil.ItemCallback<LaunchesDetails>() {
    override fun areItemsTheSame(oldItem: LaunchesDetails, newItem: LaunchesDetails): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: LaunchesDetails, newItem: LaunchesDetails): Boolean {
        return oldItem == newItem
    }
}
