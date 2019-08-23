package com.example.loginapp_5_08.data.response.current


import com.google.gson.annotations.SerializedName

data class CurrentWeatherResponseData(
    @SerializedName(value = "current")
    val currentWeatherData: CurrentWeatherData,
    val location: Location
)