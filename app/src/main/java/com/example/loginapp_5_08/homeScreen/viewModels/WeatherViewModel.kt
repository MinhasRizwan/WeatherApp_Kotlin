package com.example.loginapp_5_08.homeScreen.viewModels

import androidx.lifecycle.*
import com.example.loginapp_5_08.homeScreen.repo.WeatherRepository
import com.example.loginapp_5_08.homeScreen.openWeatherApi.response.current.CurrentWeatherResponseOWM
import com.example.loginapp_5_08.homeScreen.openWeatherApi.response.future.FutureWeatherResponseOWM
import com.example.loginapp_5_08.settings.roomDB.City

class WeatherViewModel : ViewModel(), LifecycleOwner {
    override fun getLifecycle(): Lifecycle {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private var mutableCuurrentLiveData: MutableLiveData<CurrentWeatherResponseOWM>? = null
    private var mutableFutureLiveData: MutableLiveData<FutureWeatherResponseOWM>? = null

    private var weatherRepository: WeatherRepository? = null

    fun init(city: City) {
        if (mutableCuurrentLiveData != null ) {
            return
        }
        weatherRepository = WeatherRepository.getInstance()

        mutableFutureLiveData = weatherRepository!!.getFutureWeatherByLocation(city.latitude, city.longitude)
    }

    fun getFutureWeatherRepository(): LiveData<FutureWeatherResponseOWM>? {
        return mutableFutureLiveData
    }

}