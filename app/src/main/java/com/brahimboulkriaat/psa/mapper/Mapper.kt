package com.brahimboulkriaat.psa.mapper

interface Mapper<Entity, Domain> {
    fun mapFromEntity(entity: Entity): Domain

    fun mapToEntity(domain: Domain): Entity

    fun mapFromEntities(entities: List<Entity>): List<Domain>
}