package com.brahimboulkriaat.psa.retrofit

import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface PsaService {

    @GET("cities")
    suspend fun getAll(): List<CityNetwork>

    @GET("cities")
    suspend fun get(@Path("id") id: Long): CityNetwork

    @POST("cities")
    suspend fun post(@Body cityNetwork: CityNetwork): CityNetwork

    @PUT("cities/{id}")
    suspend fun put(@Path("id") id: Long, @Body city: CityNetwork): CityNetwork

    @PATCH("cities/{id}")
    suspend fun patch(@Path("id") id: Long, @Body city: CityNetwork): CityNetwork

    @DELETE("cities/{id}")
    suspend fun delete(@Path("id") id: Long, @Body city: CityNetwork): CityNetwork

}