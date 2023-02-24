package com.brahimboulkriaat.psa.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class WeatherNetwork(
    @SerializedName("lat")
    @Expose
    val lat: Double,
    @SerializedName("lon")
    @Expose
    val lon: Double,
    @SerializedName("timezone")
    @Expose
    val timeZone: String,
    @SerializedName("timezone_offset")
    @Expose
    val timezoneOffset: Int
)