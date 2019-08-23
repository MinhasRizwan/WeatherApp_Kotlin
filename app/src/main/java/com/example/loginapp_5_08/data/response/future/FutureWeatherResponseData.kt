package com.example.loginapp_5_08.data.response.future


import com.google.gson.annotations.SerializedName

data class FutureWeatherResponseData(
    @SerializedName(value = "forecast")
    val futureWeatherData : FutureWeatherData,
    val alert: Alert,
    val current: Current,
    val location: Location
)