package com.irv205.xpacex.domain

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("code") val status_code: Int?,
    @SerializedName("message") val message: String?
) {
    companion object {
        fun empty() = ErrorResponse(null, null)
    }
}