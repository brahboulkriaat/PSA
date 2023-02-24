package com.brahimboulkriaat.psa.mapper

import com.brahimboulkriaat.psa.model.Weather
import com.brahimboulkriaat.psa.retrofit.WeatherNetwork
import javax.inject.Inject

class WeatherNetworkMapper @Inject constructor() : Mapper<WeatherNetwork, Weather> {
    override fun mapFromEntity(entity: WeatherNetwork): Weather = Weather(
        lat = entity.lat,
        lon = entity.lon,
        timeZone = entity.timeZone,
        timezoneOffset = entity.timezoneOffset
    )

    override fun mapToEntity(domain: Weather): WeatherNetwork {
        TODO("Not yet implemented")
    }

    override fun mapFromEntities(entities: List<WeatherNetwork>): List<Weather> {
        TODO("Not yet implemented")
    }
}