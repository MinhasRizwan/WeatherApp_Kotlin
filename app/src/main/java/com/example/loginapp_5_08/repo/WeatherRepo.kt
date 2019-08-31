package com.example.loginapp_5_08.repo

import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.loginapp_5_08.data.OpenWeatherApiService
import com.example.loginapp_5_08.data.db.CurrentWeatherData
import com.example.loginapp_5_08.data.response.response.current.CurrentWeatherResponseOWM
import com.example.loginapp_5_08.homeScreen.CurrentStatusContentAdapter
import kotlinx.android.synthetic.main.fragment_home_screen.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class WeatherRepo {

    private lateinit var openWeatherApiService: OpenWeatherApiService
    lateinit var currentWeatherResponseOWM: CurrentWeatherResponseOWM
    // ...

    //fun getCurrentWeatherResponse(): LiveData<CurrentWeatherData>
    suspend fun getCurrentWeatherResponse(): CurrentWeatherData {

        openWeatherApiService = OpenWeatherApiService()
        // This isn't an optimal implementation. We'll fix it later.

        //currentWeatherResponseOWM = fetchWeather()

        val data: CurrentWeatherData = CurrentWeatherData(
            currentWeatherResponseOWM.currentWeatherEntry.temp,
            currentWeatherResponseOWM.dt.toString(),
            currentWeatherResponseOWM.weather[0].icon,
            currentWeatherResponseOWM.name,
            currentWeatherResponseOWM.weather[0].description)

        return data

    }

/*    suspend fun fetchWeather(): CurrentWeatherResponseOWM{
        val currentWeatherResponseOWM = openWeatherApiService.getCurrentWeather("London,uk")

        return currentWeatherResponseOWM
    }
*/
}