package com.brahimboulkriaat.psa.retrofit

import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface CityService {

    @GET("cities")
    suspend fun getAll(): List<CityNetwork>

    @GET("cities/{id}")
    suspend fun get(@Path("id") id: Int): CityNetwork

    @POST("cities")
    suspend fun post(@Body cityNetwork: CityNetwork): CityNetwork

    @PUT("cities/{id}")
    suspend fun put(@Body city: CityNetwork, @Path("id") id: Int = city.id): CityNetwork

    @PATCH("cities/{id}")
    suspend fun patch(@Path("id") id: Long, @Body city: CityNetwork): CityNetwork

    @DELETE("cities/{id}")
    suspend fun delete(@Path("id") id: Int): CityNetwork

}