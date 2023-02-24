package com.brahimboulkriaat.psa.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PsaNetwork(
    @SerializedName("id")
    @Expose
    val id: Long
)