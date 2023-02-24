package com.brahimboulkriaat.psa.mapper

import com.brahimboulkriaat.psa.model.City
import com.brahimboulkriaat.psa.retrofit.CityNetwork
import javax.inject.Inject

class NetworkMapper @Inject constructor() : Mapper<CityNetwork, City> {
    override fun mapFromEntity(entity: CityNetwork): City =
        City(entity.id, entity.name, entity.lat, entity.lon)

    override fun mapToEntity(domain: City): CityNetwork =
        CityNetwork(domain.id, domain.name, domain.lat, domain.lon)

    override fun mapFromEntities(entities: List<CityNetwork>): List<City> =
        entities.map(::mapFromEntity)
}