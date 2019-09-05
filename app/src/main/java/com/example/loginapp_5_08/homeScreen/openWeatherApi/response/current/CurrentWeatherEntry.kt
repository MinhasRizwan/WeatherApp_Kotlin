package com.example.loginapp_5_08.homeScreen.openWeatherApi.response.current

import com.google.gson.annotations.SerializedName

class CurrentWeatherEntry(
    val humidity: Int,
    val pressure: Int,
    val temp: Double,
    @SerializedName("temp_max")
    val tempMax: Double,
    @SerializedName("temp_min")
    val tempMin: Double
)