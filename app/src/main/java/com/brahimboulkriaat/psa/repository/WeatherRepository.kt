package com.brahimboulkriaat.psa.repository

import com.brahimboulkriaat.psa.mapper.WeatherNetworkMapper
import com.brahimboulkriaat.psa.model.Weather
import com.brahimboulkriaat.psa.retrofit.WeatherService
import com.brahimboulkriaat.psa.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherService: WeatherService,
    private val weatherNetworkMapper: WeatherNetworkMapper
) {

    suspend fun get(lon: Float, lat: Float): Flow<DataState<Weather>> = flow {
        try {
            val weatherNetwork = weatherService.get(lon = lon, lat = lat)
            val weather = weatherNetworkMapper.mapFromEntity(weatherNetwork)
            emit(DataState.Success(weather))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}