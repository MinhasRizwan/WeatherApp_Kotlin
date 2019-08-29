package com.example.loginapp_5_08.data

import androidx.lifecycle.*
import com.example.loginapp_5_08.Weather
import com.example.loginapp_5_08.data.response.response.current.CurrentWeatherResponseOWM
import com.example.loginapp_5_08.data.response.response.future.FutureWeatherResponseOWM
import com.example.loginapp_5_08.homeScreen.HomeFragment


class WeatherViewModel : ViewModel(){

    private var mutableCuurrentLiveData: MutableLiveData<CurrentWeatherResponseOWM>? = null
    private var mutableFutureLiveData: MutableLiveData<FutureWeatherResponseOWM>? = null

    private var weatherRepository: WeatherRepository? = null

    fun init() {
        if (mutableCuurrentLiveData != null ) {
            return
        }
        weatherRepository = WeatherRepository.getInstance()

        mutableCuurrentLiveData = weatherRepository!!.getCurrentWeather()
        mutableFutureLiveData = weatherRepository!!.getFutureWeather()

    }

    fun getCurrentWeatherRepository(): LiveData<CurrentWeatherResponseOWM>? {
        return mutableCuurrentLiveData
    }

    fun getFutureWeatherRepository(): LiveData<FutureWeatherResponseOWM>? {
        return mutableFutureLiveData
    }
}