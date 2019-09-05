package com.example.loginapp_5_08.homeScreen.openWeatherApi.response.future

data class FutureWeatherResponseOWM(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<X>,
    val message: Double
)