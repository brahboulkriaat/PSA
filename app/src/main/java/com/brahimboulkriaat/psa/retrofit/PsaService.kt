package com.brahimboulkriaat.psa.retrofit

import retrofit2.http.GET

interface PsaService {

    @GET("psas")
    suspend fun getAll(): List<PsaNetwork>
}