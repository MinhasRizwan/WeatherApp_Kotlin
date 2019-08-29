package com.example.loginapp_5_08.data.response.response.current

import com.google.gson.annotations.SerializedName

data class CurrentWeatherResponseOWM(
    val dt: Int,
    val id: Int,
    @SerializedName("main")
    val currentWeatherEntry: CurrentWeatherEntry,
    val name: String,
    val timezone: Int,
    val weather: List<Weather>
)