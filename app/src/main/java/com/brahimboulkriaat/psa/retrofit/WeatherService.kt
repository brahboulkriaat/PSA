package com.brahimboulkriaat.psa.retrofit

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("onecall")
    suspend fun get(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("exclude") exclude: String = "current,minutely,hourly,daily,alerts",
        @Query("appid") appId: String = "343adb07ba22708eeb36a0d0cb19fe5c"
    ): WeatherNetwork
}