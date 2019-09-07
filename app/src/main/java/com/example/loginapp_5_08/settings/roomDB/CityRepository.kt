package com.example.loginapp_5_08.settings.roomDB

import androidx.lifecycle.LiveData

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class CityRepository(private val cityDao: CityDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allCities: LiveData<List<City>> = cityDao.getAllCities()


    // The suspend modifier tells the compiler that this must be called from a
    // coroutine or another suspend function.
    suspend fun insert(city: City) {
        cityDao.insert(city)
    }

    suspend fun delete(city:City){
        cityDao.deleteCity(city)
    }
}