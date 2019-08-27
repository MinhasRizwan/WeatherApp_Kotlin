package com.example.loginapp_5_08.data.response.response2.current

data class CurrentWeatherResponseOWM(
    val dt: Int,
    val id: Int,
    val main: Main,
    val name: String,
    val timezone: Int,
    val weather: List<Weather>
)