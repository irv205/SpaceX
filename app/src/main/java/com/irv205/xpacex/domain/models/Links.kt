package com.irv205.xpacex.domain.models

import com.google.gson.annotations.SerializedName

data class Links (
    @SerializedName("patch")
    val mission_patch: Patch,
    @SerializedName("flickr")
    val flickr: Flickr,
    @SerializedName("youtube_id")
    val video_link: String,
) {
    companion object {
        fun empty() = Links(
            Patch.emptyPatch(),
            Flickr.emptyFlickr(),
            ""
        )
    }
}

data class Patch(
    @SerializedName("small")
    val small: String,
) {
    companion object {
        fun emptyPatch() = Patch("")
    }
}

data class Flickr(
    @SerializedName("original")
    val original: List<String>
) {
    companion object {
        fun emptyFlickr() = Flickr(emptyList())
    }
}