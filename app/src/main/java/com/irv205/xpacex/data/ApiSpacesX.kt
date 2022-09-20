package com.irv205.xpacex.data

import com.irv205.xpacex.domain.models.LaunchesDetails
import com.irv205.xpacex.domain.models.RocketResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiSpacesX {

    @GET("launches")
    fun getLaunchesList(): Call<List<LaunchesDetails>>

    @GET("launches/{launche_id}")
    fun getLauncheByID(
        @Path("launche_id") id: String
    ): Call<LaunchesDetails>

    @GET("rockets/{rocket_id}")
    fun getRocketInfo(
        @Path("rocket_id") rocket: String
    ): Call<RocketResponse>
}