package com.example.loginapp_5_08.settings.roomDB

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CityDao {

    @Query("SELECT * from city_table")
    fun getAllCities(): LiveData<List<City>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(city: City)

    @Query("DELETE FROM city_table")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteCity(city:City)

    @Query("SELECT * FROM city_table WHERE name LIKE :cityName")
    fun findByCityName(cityName: String): LiveData<List<City>>


}