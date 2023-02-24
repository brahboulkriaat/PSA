package com.brahimboulkriaat.psa.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CityNetwork(
    @SerializedName("id")
    @Expose
    val id: Long,

    @SerializedName("name")
    @Expose
    val name: String,

    @SerializedName("lat")
    @Expose
    val lat: Double,

    @SerializedName("lon")
    @Expose
    val lon: Double
)