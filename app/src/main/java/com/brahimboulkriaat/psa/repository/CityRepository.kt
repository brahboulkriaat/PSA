package com.brahimboulkriaat.psa.repository

import com.brahimboulkriaat.psa.mapper.EntityMapper
import com.brahimboulkriaat.psa.mapper.NetworkMapper
import com.brahimboulkriaat.psa.model.City
import com.brahimboulkriaat.psa.retrofit.CityService
import com.brahimboulkriaat.psa.room.CityDao
import com.brahimboulkriaat.psa.util.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class CityRepository @Inject constructor(
    private val cityDao: CityDao,
    private val psaService: CityService,
    private val entityMapper: EntityMapper,
    private val networkMapper: NetworkMapper
) {

    suspend fun getAll(): Flow<DataState<List<City>>> = flow<DataState<List<City>>> {
        emit(DataState.Loading)
        delay(3000)

        try {
            val networkEntities = psaService.getAll()
            val domains = networkMapper.mapFromEntities(networkEntities)
            cityDao.insert(*entityMapper.mapToEntities(domains).toTypedArray())
            val cacheEntities = cityDao.getAll()
            emit(DataState.Success<List<City>>(entityMapper.mapFromEntities(cacheEntities)))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}