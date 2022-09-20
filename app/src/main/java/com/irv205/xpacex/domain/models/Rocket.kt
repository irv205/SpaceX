package com.irv205.xpacex.domain.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class RocketResponse (
    @SerializedName("id")
    val rocket_id: String,
    @SerializedName("name")
    val rocket_name: String,
    @SerializedName("type")
    val rocket_type: String,
    @SerializedName("active")
    val active: Boolean,
    @SerializedName("first_flight")
    val first_flight: Date?,
    @SerializedName("description")
    val description: String,
    @SerializedName("flickr_images")
    val flickr_images: List<String>,
    @SerializedName("country")
    val country: String,
    @SerializedName("company")
    val company: String,
) {
    companion object {
        fun empty() = RocketResponse(
            "",
            "",
            "",
            false,
            null,
            "",
            emptyList(),
            "",
            ""
        )
    }
}

data class RocketFlickr(
    val url: String
)