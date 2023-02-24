package com.brahimboulkriaat.psa.di

import android.content.Context
import androidx.room.Room
import com.brahimboulkriaat.psa.room.CityDao
import com.brahimboulkriaat.psa.room.PsaDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RoomModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context): PsaDatabase = Room.databaseBuilder(context, PsaDatabase::class.java, PsaDatabase.DATABASE_NAME).build()

    @Provides
    @Singleton
    fun providesCityDao(psaDatabase: PsaDatabase): CityDao = psaDatabase.cityDao()
}