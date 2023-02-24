package com.brahimboulkriaat.psa.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PsaEntity::class], version = 1)
abstract class PsaDatabase: RoomDatabase() {
    abstract fun psaDao(): PsaDao
}