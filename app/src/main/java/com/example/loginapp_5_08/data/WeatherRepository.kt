package com.example.loginapp_5_08.data

import androidx.lifecycle.*
import com.example.loginapp_5_08.data.response.response.current.CurrentWeatherResponseOWM
import com.example.loginapp_5_08.data.response.response.future.FutureWeatherResponseOWM
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.coroutineContext


class WeatherRepository : LifecycleOwner {

    override fun getLifecycle(): Lifecycle {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private var openWeatherApiService = OpenWeatherApiService()

    companion object{
        fun getInstance():WeatherRepository{
            val weatherRepository = WeatherRepository()

            return weatherRepository
        }
    }

    fun WeatherRepository() {
        openWeatherApiService = OpenWeatherApiService()
    }


    fun getCurrentWeather(): MutableLiveData<CurrentWeatherResponseOWM>{
        val currentData = MutableLiveData<CurrentWeatherResponseOWM>()
        openWeatherApiService.getCurrentWeather("London,uk").enqueue(object : Callback<CurrentWeatherResponseOWM> {

            override fun onResponse(
                call: Call<CurrentWeatherResponseOWM>,
                response: Response<CurrentWeatherResponseOWM>
            ) {
                if (response.isSuccessful()) {
                    currentData.setValue(response.body())
                }
            }

            override fun onFailure(call: Call<CurrentWeatherResponseOWM>, t: Throwable) {
                currentData.setValue(null)
            }
        })
        return currentData
    }


    fun getFutureWeather(): MutableLiveData<FutureWeatherResponseOWM>{
        val futureData = MutableLiveData<FutureWeatherResponseOWM>()
        openWeatherApiService.getFutureWeather("London,uk").enqueue(object : Callback<FutureWeatherResponseOWM> {

            override fun onResponse(
                call: Call<FutureWeatherResponseOWM>,
                response: Response<FutureWeatherResponseOWM>
            ) {
                if (response.isSuccessful()) {
                    futureData.setValue(response.body())
                }
            }

            override fun onFailure(call: Call<FutureWeatherResponseOWM>, t: Throwable) {
                futureData.setValue(null)
            }
        })
        return futureData
    }



}