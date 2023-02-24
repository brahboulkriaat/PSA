package com.brahimboulkriaat.psa.di

import com.brahimboulkriaat.psa.mapper.EntityMapper
import com.brahimboulkriaat.psa.mapper.NetworkMapper
import com.brahimboulkriaat.psa.repository.CityRepository
import com.brahimboulkriaat.psa.retrofit.CityService
import com.brahimboulkriaat.psa.room.CityDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/*
@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    // On a nnoté PsaRespository avec @Inject donc pas besoin du @Provides
    @Provides
    @Singleton
    fun providesPsaRepository(
        psaDao: CityDao,
        cityService: CityService,
        entityMapper: EntityMapper,
        networkMapper: NetworkMapper
    ) = CityRepository(psaDao, cityService, entityMapper, networkMapper)
}*/
