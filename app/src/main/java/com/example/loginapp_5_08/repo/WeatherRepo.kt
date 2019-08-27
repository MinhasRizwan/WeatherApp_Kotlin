package com.example.loginapp_5_08.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.loginapp_5_08.data.OpenWeatherApiService
import com.example.loginapp_5_08.data.response.response2.future.FutureWeatherResponseOWM
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherRepo {
    /*
    private val openWeatherApiService: OpenWeatherApiService = TODO()
    // ...
    fun getFutureWeatherResponse(userId: String): LiveData<FutureWeatherResponseOWM> {
        // This isn't an optimal implementation. We'll fix it later.
        val data = MutableLiveData<FutureWeatherResponseOWM>()

        openWeatherApiService.getWeekWeather("London,uk").enqueue(object : Callback<FutureWeatherResponseOWM> {
            override fun onResponse(call: Call<FutureWeatherResponseOWM>, response: Response<FutureWeatherResponseOWM>) {
                data.value = response.body()
            }
            // Error case is left out for brevity.
            override fun onFailure(call: Call<FutureWeatherResponseOWM>, t: Throwable) {
                TODO()
            }
        })
        return data
    }*/
}