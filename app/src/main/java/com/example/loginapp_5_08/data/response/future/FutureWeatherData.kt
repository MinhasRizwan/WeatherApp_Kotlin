package com.example.loginapp_5_08.data.response.future


import com.google.gson.annotations.SerializedName

data class FutureWeatherData(
    val forecastday: List<Forecastday>
)