package com.brahimboulkriaat.psa.mapper

import com.brahimboulkriaat.psa.model.City
import com.brahimboulkriaat.psa.room.CityEntity
import javax.inject.Inject

class NetworkMapper @Inject constructor() : Mapper<CityEntity, City> {
    override fun mapFromEntity(entity: CityEntity): City =
        City(entity.id, entity.name, entity.lat, entity.lon)

    override fun mapToEntity(domain: City): CityEntity =
        CityEntity(domain.id, domain.name, domain.lat, domain.lon)

    override fun mapFromEntities(entities: List<CityEntity>): List<City> =
        entities.map(::mapFromEntity)
}