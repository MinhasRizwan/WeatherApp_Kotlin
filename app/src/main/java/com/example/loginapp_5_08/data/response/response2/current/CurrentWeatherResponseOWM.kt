package com.example.loginapp_5_08.data.response.response2.current


import com.google.gson.annotations.SerializedName

data class CurrentWeatherResponseOWM(
    val base: String,
    val clouds: Clouds,
    val cod: Int,
    val coord: Coord,
    val dt: Int,
    val id: Int,
    val main: Main,
    val name: String,
    val sys: Sys,
    val timezone: Int,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind
)