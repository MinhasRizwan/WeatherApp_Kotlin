package com.example.loginapp_5_08.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.loginapp_5_08.data.response.response2.current.CurrentWeatherResponseOWM
import com.example.loginapp_5_08.data.response.response2.future.FutureWeatherResponseOWM

class HomeViewModel : ViewModel() {
    private lateinit var futureWeatherResponseOWM: LiveData<FutureWeatherResponseOWM>
    private lateinit var currentWeatherResponseOWM: LiveData<CurrentWeatherResponseOWM>

    fun getCurrentWeather() : LiveData<CurrentWeatherResponseOWM> {

        return currentWeatherResponseOWM
    }

    fun getFutureWeather() : LiveData<FutureWeatherResponseOWM> {

        return futureWeatherResponseOWM
    }

    fun HomeViewModel(){


    }




}