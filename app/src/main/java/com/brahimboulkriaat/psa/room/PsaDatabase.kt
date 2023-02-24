package com.brahimboulkriaat.psa.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CityEntity::class], version = 1)
abstract class PsaDatabase: RoomDatabase() {
    abstract fun cityDao(): CityDao

    companion object {
        const val DATABASE_NAME = "db_psa"
    }
}