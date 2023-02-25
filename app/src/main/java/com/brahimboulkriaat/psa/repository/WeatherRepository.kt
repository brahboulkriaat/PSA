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

    suspend fun get(): Flow<DataState<Weather>> = flow<DataState<Weather>> {
        emit(DataState.Loading)

        try {
            val weatherNetwork = weatherService.get(lat = 48.856614, lon = 2.352222)
            val weather = weatherNetworkMapper.mapFromEntity(weatherNetwork)
            emit(DataState.Success<Weather>(weather))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}