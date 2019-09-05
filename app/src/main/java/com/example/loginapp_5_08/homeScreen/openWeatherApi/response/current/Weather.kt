package com.example.loginapp_5_08.homeScreen.openWeatherApi.response.current

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)