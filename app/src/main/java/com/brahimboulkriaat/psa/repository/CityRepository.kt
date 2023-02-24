package com.brahimboulkriaat.psa.repository

import com.brahimboulkriaat.psa.mapper.CityEntityMapper
import com.brahimboulkriaat.psa.mapper.CityNetworkMapper
import com.brahimboulkriaat.psa.model.City
import com.brahimboulkriaat.psa.retrofit.CityService
import com.brahimboulkriaat.psa.room.CityDao
import com.brahimboulkriaat.psa.util.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.Exception

class CityRepository @Inject constructor(
    private val cityDao: CityDao,
    private val cityService: CityService,
    private val entityMapper: CityEntityMapper,
    private val networkMapper: CityNetworkMapper
) {

    suspend fun getAll(): Flow<DataState<List<City>>> = flow<DataState<List<City>>> {
        emit(DataState.Loading)
        delay(3000)

        try {
            val networkEntities = cityService.getAll()
            val domains = networkMapper.mapFromEntities(networkEntities)
            cityDao.insert(*entityMapper.mapToEntities(domains).toTypedArray())
            val cacheEntities = cityDao.getAll()
            emit(DataState.Success<List<City>>(entityMapper.mapFromEntities(cacheEntities)))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    suspend fun get(id: Int): Flow<DataState<City>> = flow <DataState<City>>{
        emit(DataState.Loading)
        delay(3000)

        try {
            val networkEntity = cityService.get(id)
            val domain = networkMapper.mapFromEntity(networkEntity)
            cityDao.insert(entityMapper.mapToEntity(domain))
            val entity = cityDao.get(id)
            emit(DataState.Success<City>(entityMapper.mapFromEntity(entity)))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    suspend fun create(city: City): Flow<DataState<City>> = flow<DataState<City>> {
        emit(DataState.Loading)
        delay(3000)

        try {
            val networkEntity = networkMapper.mapToEntity(city)
            val domain = networkMapper.mapFromEntity(cityService.post(networkEntity))
            val rows = cityDao.insert(entityMapper.mapToEntity(domain))
            val cachedEntity = cityDao.get(city.id)
            emit(DataState.Success<City>(entityMapper.mapFromEntity(cachedEntity)))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    suspend fun update(city: City): Flow<DataState<City>> = flow {
        emit(DataState.Loading)
        try {
            val networkEntity = cityService.put(networkMapper.mapToEntity(city))
            val domain = networkMapper.mapFromEntity(networkEntity)
            cityDao.update(entityMapper.mapToEntity(domain))
            val cachedEntity = cityDao.get(city.id)
            emit(DataState.Success<City>(entityMapper.mapFromEntity(cachedEntity)))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    suspend fun delete(city: City): Flow<DataState<Int>> = flow {
        emit(DataState.Loading)
        try {
            val networkEntity = cityService.delete(city.id)
            val domain = networkMapper.mapFromEntity(networkEntity)
            val cachedEntity = entityMapper.mapToEntity(domain)
            val id = cityDao.delete(cachedEntity)
            emit(DataState.Success<Int>(id))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}