package com.example.loginapp_5_08.homeScreen.repo

import androidx.lifecycle.*
import com.example.loginapp_5_08.homeScreen.openWeatherApi.OpenWeatherApiService
import com.example.loginapp_5_08.homeScreen.openWeatherApi.response.current.CurrentWeatherResponseOWM
import com.example.loginapp_5_08.homeScreen.openWeatherApi.response.future.FutureWeatherResponseOWM
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class WeatherRepository : LifecycleOwner {

    override fun getLifecycle(): Lifecycle {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private var openWeatherApiService = OpenWeatherApiService()

    companion object{
        fun getInstance(): WeatherRepository {
            val weatherRepository = WeatherRepository()

            return weatherRepository
        }
    }

    //Return CurrentWeather API response
    fun getCurrentWeather(): MutableLiveData<CurrentWeatherResponseOWM>{
        val currentData = MutableLiveData<CurrentWeatherResponseOWM>()
        openWeatherApiService.getCurrentWeather("London,uk").enqueue(object : Callback<CurrentWeatherResponseOWM> {

            override fun onResponse(
                call: Call<CurrentWeatherResponseOWM>,
                response: Response<CurrentWeatherResponseOWM>
            ) {
                if (response.isSuccessful) {
                    currentData.value = response.body()
                }
            }

            override fun onFailure(call: Call<CurrentWeatherResponseOWM>, t: Throwable) {
                currentData.value = null
            }
        })
        return currentData
    }

    //Return FutureWeather API response
    fun getFutureWeather(city:String): MutableLiveData<FutureWeatherResponseOWM>{
        val futureData = MutableLiveData<FutureWeatherResponseOWM>()
        openWeatherApiService.getFutureWeather(city).enqueue(object : Callback<FutureWeatherResponseOWM> {

            override fun onResponse(
                call: Call<FutureWeatherResponseOWM>,
                response: Response<FutureWeatherResponseOWM>
            ) {
                if (response.isSuccessful) {
                    futureData.value = response.body()
                }
            }

            override fun onFailure(call: Call<FutureWeatherResponseOWM>, t: Throwable) {
                futureData.value = null
            }
        })
        return futureData
    }

    //Return Weather API response (Searched By Location)

    fun getFutureWeatherByLocation(lati:Double, longi:Double): MutableLiveData<FutureWeatherResponseOWM>{
        val futureData = MutableLiveData<FutureWeatherResponseOWM>()
        openWeatherApiService.getFutureWeatherByLocation(lati.toString(),longi.toString()).enqueue(object : Callback<FutureWeatherResponseOWM> {

            override fun onResponse(
                call: Call<FutureWeatherResponseOWM>,
                response: Response<FutureWeatherResponseOWM>
            ) {
                if (response.isSuccessful) {
                    futureData.value = response.body()
                }
            }

            override fun onFailure(call: Call<FutureWeatherResponseOWM>, t: Throwable) {
                futureData.value = null
            }
        })
        return futureData
    }

}