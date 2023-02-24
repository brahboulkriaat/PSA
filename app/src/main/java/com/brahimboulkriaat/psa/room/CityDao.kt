package com.brahimboulkriaat.psa.room

import androidx.room.*

@Dao
interface CityDao {

    @Query("SELECT * FROM ${CityEntity.ENTITY_NAME}")
    suspend fun getAll(): List<CityEntity>

    @Query("SELECT * FROM ${CityEntity.ENTITY_NAME} WHERE id=:id")
    suspend fun getById(id: Long): CityEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg psa: CityEntity): List<Long>

    @Update
    suspend fun update(vararg psa: CityEntity): Int

    @Delete
    suspend fun delete(vararg psa: CityEntity): Int
}