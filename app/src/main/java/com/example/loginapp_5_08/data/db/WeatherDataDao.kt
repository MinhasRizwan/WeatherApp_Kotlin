package com.example.loginapp_5_08.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WeatherDataDao {

    @Insert
    fun insertHourlyWeatherData(hourlyWeatherData: HourlyWeatherData)

    @Insert
    fun insertCurrentWeatherData(currentWeatherData: CurrentWeatherData)

    @Insert
    fun insertWeeklyWeatherData(weeklyWeatherData: WeeklyWeatherData)

    @Query("Select * from hourlyWeatherData where dataId = 1")
    fun getHourlyWeather(): LiveData<HourlyWeatherData>

    @Query("Select * from weeklyWeatherData where dataId = 1")
    fun getWeeklyWeather(): LiveData<WeeklyWeatherData>

    @Query("Select * from currentWeatherData where dataId = 1")
    fun getCurrentWeather(): LiveData<CurrentWeatherData>



}