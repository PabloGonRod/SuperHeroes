package com.pgonrod.superheroes.data.powerstats.remote.api

import com.google.gson.annotations.SerializedName

data class PowerStatsApiModel(
    @SerializedName("intelligence") val intelligence: Int,
    @SerializedName("speed") val speed: Int,
    @SerializedName("combat") val combat: Int
)
