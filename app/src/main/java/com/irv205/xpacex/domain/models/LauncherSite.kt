package com.irv205.xpacex.domain.models

import com.google.gson.annotations.SerializedName

data class LauncherSite(
    @SerializedName("site_name")
    val site_name: String,
    @SerializedName("site_name_long")
    val site_name_long: String
)