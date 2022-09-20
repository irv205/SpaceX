package com.irv205.xpacex.domain.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class LaunchesDetails(
    @SerializedName("id")
    val id: String,
    @SerializedName("flight_number")
    val flight_number: String,
    @SerializedName("name")
    val mission_name: String,
    @SerializedName("date_utc")
    val launch_date_utc: Date?,
    @SerializedName("success")
    val launch_success: Boolean,
    @SerializedName("links")
    val links: Links,
    @SerializedName("details")
    val details: String,
    @SerializedName("rocket")
    val rocket: String
) {
    companion object {
        fun emptyLaunche() = LaunchesDetails(
            "",
            "",
            "",
            null,
            false,
            Links.empty(),
            "",
            ""
        )
    }
}