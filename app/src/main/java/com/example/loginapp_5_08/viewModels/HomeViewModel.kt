package com.example.loginapp_5_08.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loginapp_5_08.Weather
import com.example.loginapp_5_08.data.OpenWeatherApiService
import com.example.loginapp_5_08.data.db.CurrentWeatherData
import com.example.loginapp_5_08.data.response.response.future.FutureWeatherResponseOWM
import com.example.loginapp_5_08.repo.WeatherRepo

class HomeViewModel : ViewModel() {
    //private lateinit var currentWeatherResponseOWM: LiveData<CurrentWeatherResponseOWM>


    val repo = WeatherRepo()
    suspend fun getDataFromRepo() : CurrentWeatherData{
        return repo.getCurrentWeatherResponse()
    }

}